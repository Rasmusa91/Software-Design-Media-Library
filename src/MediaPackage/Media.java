package MediaPackage;

import java.util.ArrayList;

public class Media 
{
	private String id;
	private String name;
	private float price;
	private int amount;
	private MediaStatus status;
	private ArrayList<String> queuedUsers;
	
	public Media(String id, String name, float price, int amount, MediaStatus status, ArrayList<String> queuedUsers)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.status = status;
		this.queuedUsers = queuedUsers;
	}
	
	public String getId()
	{
		return id;		
	}
	
	public String getName()
	{
		return name;		
	}
	
	public float getPrice()
	{
		return price;		
	}
	
	public int getAmount()
	{
		return amount;		
	}
	
	public boolean editAmount(int amount)
	{
		this.amount += amount;
		
		return true;
	}
	
	public MediaStatus getStatus()
	{
		return status;		
	}

	public boolean addUserToQueue(String userId)
	{
		return queuedUsers.add(userId);
	}
	
	public ArrayList<String> getQueuedUsers()
	{
		return queuedUsers;
	}
	
	public int getUserQueuePosition(String userId)
	{
		return queuedUsers.indexOf(userId);
	}
	
	public boolean editData(String name, float price, int amount, MediaStatus status)
	{
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.status = status;
		
		return true;
	}
}
