package MediaPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import DefaultPackage.*;
import UserPackage.*;

public abstract class MediaHandlerState extends Observable implements IObserver
{
	protected ArrayList<Media> media;
	private IMediaHandlerStateChangeCallback stateChangeCallback;
	protected User user;
	
	public MediaHandlerState(IMediaHandlerStateChangeCallback stateChangeCallback, boolean initialize)
	{
		this.stateChangeCallback = stateChangeCallback;
		
		if(initialize) {
			initialize();			
		}
	}
	
	private void initialize()
	{
		media = new ArrayList<Media>();
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT *, "
				+ "(SELECT GROUP_CONCAT(RentalQueue.customerId) FROM RentalQueue WHERE RentalQueue.mediaId = Media.id ORDER BY RentalQueue.addedDate ASC) AS queue "
				+ "FROM Media");
		
		
		for (HashMap<String, Object> mediaData : result) 
		{
			Media newMedia = null;
			
			String id = mediaData.get("id").toString();
			String name = (String) mediaData.get("name");
			float price = (float) ((double) mediaData.get("price"));
			int amount = (int) mediaData.get("amount");
			MediaStatus status = MediaStatus.valueOf((String) mediaData.get("status"));
			MediaType type = MediaType.valueOf((String) mediaData.get("mediaType"));
			ArrayList<String> queue = new ArrayList<String>();
			
			String queueData = (String) mediaData.get("queue");
			if(queueData != null) 
			{
				String[] splitQueueData = queueData.split(",");
				for (String string : splitQueueData) {
					queue.add(string);
				}
			}
			
			if(type == MediaType.AudioBook) {
				newMedia = new AudioBook(id, name, price, amount, status, queue);
			}
			else if(type == MediaType.EBook) {
				newMedia = new EBook(id, name, price, amount, status, queue);
			}	
			
			if(newMedia != null) {
				media.add(newMedia);
			}
		}
	}
		
	public boolean rent(Media media)
	{
		return false;
	}
	
	public boolean addMediaItem(String name, float price, int amount, MediaStatus status, MediaType type)
	{
		return false;
	}
	
	public boolean editMedia(Media media, String name, float price, int amount, MediaStatus status, MediaType type)
	{
		return false;
	}

	public boolean addCustomerToQueue(Media media)
	{
		return false;
	}
	
	
	
	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
		observer.Update(media);
	}

	@Override
	public void Update(Object object) 
	{
		user = (User) object;
		
		MediaHandlerState handler = null;
		if(user == null) {
			handler = new MediaHandlerStateNone(stateChangeCallback, false);
		}
		else if(user.isStaff()) {
			handler = new MediaHandlerStateStaff(stateChangeCallback, false);			
		}
		else {
			handler = new MediaHandlerStateCustomer(stateChangeCallback, false);
		}

		handler.setMedia(media);
		handler.setUser(user);
		stateChangeCallback.stateChange(handler);
	}
	
	private void setUser(User user)
	{
		this.user = user;
	}
	
	private void setMedia(ArrayList<Media> media)
	{
		this.media = media;
	}
}
