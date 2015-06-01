package UserPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer extends User 
{
	private float balance;
	private ArrayList<RentedMedia> rentedMedia;
	
	public Customer(String id, String name, float balance) {
		super(id, name);
		
		this.balance = balance;
		
		rentedMedia = new ArrayList<RentedMedia>();
	}

	@Override
	public boolean isStaff() {
		return false;
	}
	
	public float getBalance()
	{
		return balance;		
	}
	
	public boolean editBalance(float amount)
	{
		this.balance += amount;
		
		return true;
	}
	
	public RentedMedia addRentedMedia(String mediaID, Date expirationDate)
	{
		RentedMedia newRentedMedia = new RentedMedia(mediaID, expirationDate);
		rentedMedia.add(newRentedMedia);

		return newRentedMedia;
	}
	
	public ArrayList<RentedMedia> getRentedMedia()
	{
		return rentedMedia;
	}
}
