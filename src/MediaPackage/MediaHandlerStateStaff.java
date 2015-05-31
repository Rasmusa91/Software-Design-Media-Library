package MediaPackage;

import java.util.ArrayList;
import java.util.HashMap;

import DefaultPackage.*;

public class MediaHandlerStateStaff extends MediaHandlerState {

	public MediaHandlerStateStaff(IMediaHandlerStateChangeCallback stateChangeCallback, boolean initialize) 
	{
		super(stateChangeCallback, initialize);
	}
	
	public boolean addMediaItem(String name, float price, int amount, MediaStatus status, MediaType type)
	{
		boolean success = false;
		
		DatabaseHandler.executeQuery(""
				+ "INSERT INTO Media "
				+ "(name, price, amount, status, mediaType) "
				+ "VALUES('" + name + "', '" + price + "', '" + amount + "', '" + status + "', '" + type + "')");
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT MAX(id) AS LastID FROM Media");
		String lastID = result.get(0).get("LastID").toString();
		
		if(type == MediaType.AudioBook) {
			success = media.add(new AudioBook(lastID, name, price, amount, status, null));
		}
		else if(type == MediaType.AudioBook) {
			success = media.add(new EBook(lastID, name, price, amount, status, null));
		}
		
		return success;
	}
	
	public boolean editMedia(Media media, String name, float price, int amount, MediaStatus status, MediaType type)
	{
		boolean success = media.editData(name, price, amount, status);
		
		if(success)
		{
			DatabaseHandler.executeQuery("UPDATE Media "
					+ "SET name = '" + name + "', price = '" + price + "', amount = '" + amount + "', status = '" + status + "' "
					+ "WHERE id = '" + media.getId() + "'");
		}
				
		return success;
	}	
}
