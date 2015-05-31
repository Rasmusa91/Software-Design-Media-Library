package StatisticsPackage;

import java.util.Date;

public class Transaction 
{
	private String id;
	private String customerId;
	private TransactionType type;
	private float value;
	private Date date;
	
	public Transaction(String id, String customerId, TransactionType type, float value, Date date)
	{
		this.id = id;
		this.customerId = customerId;
		this.type = type;
		this.value = value;
		this.date = date;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getCustomerId()
	{
		return customerId;
	}
	
	public TransactionType getType()
	{
		return type;
	}
	
	public float getValue()
	{
		return value;
	}

	public Date getDate()
	{
		return date;
	}
}
