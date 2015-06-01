package MediaPackage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import DefaultPackage.DatabaseHandler;
import UserPackage.*;

public class MediaHandlerStateCustomer extends MediaHandlerState 
{
	public MediaHandlerStateCustomer(IMediaHandlerStateChangeCallback stateChangeCallback, boolean initialize) 
	{
		super(stateChangeCallback, initialize);
	}
	
	@Override
	public boolean rent(Media media)
	{
		boolean success = false;
		
		if(((Customer) user).getBalance() >= media.getPrice() && media.getStatus() == MediaStatus.InStock && media.getAmount() > 0) 
		{
			media.editAmount(-1);				
			
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 14);
			date = calendar.getTime();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String dateString = dateFormat.format(date);			
			
			RentedMedia rm = ((Customer) user).addRentedMedia(media.getId(), date);
			
			DatabaseHandler.executeQuery("INSERT INTO RentedMedia "
					+ "(userId, mediaId, expirationDate) "
					+ "VALUES ('" + user.getId() + "', '" + rm.getMediaID()  + "', '" + dateString + "')");
			
			DatabaseHandler.executeQuery("UPDATE Media SET amount = amount - 1 WHERE id = '"+ media.getId() +"'");
			
			success = true;
		}
		
		return success;
	}	
	
	@Override
	public boolean addCustomerToQueue(Media media)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();		
		String dateString = dateFormat.format(date);			
		
		media.addUserToQueue(user.getId());
		
		DatabaseHandler.executeQuery("INSERT INTO RentalQueue "
				+ "(customerId, mediaId, addedDate) "
				+ "VALUES('" + user.getId() + "', '" + media.getId() + "', '" + dateString + "')");
		
		return true;
	}
}
