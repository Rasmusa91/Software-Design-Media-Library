package TicketPackage;

public class Ticket 
{
	private String id;
	private TicketStatus status;
	private String customerId;
	private String customerMessage;
	private String staffId;
	private String staffMessage;
	
	public Ticket(String id, String customerId, String customerMessage)
	{
		this.id = id;
		this.customerId = customerId;
		this.customerMessage = customerMessage;
		this.status = TicketStatus.Unresolved;
	}
	
	public String getId()
	{
		return id;
	}
	
	public TicketStatus getStatus()
	{
		return status;
	}
	
	public String getCustomerId()
	{
		return customerId;
	}
	
	public String getCustomerMessage()
	{
		return customerMessage;
	}
	
	public String getStaffId()
	{
		return staffId;
	}
	
	public String getStaffMessage()
	{
		return staffMessage;
	}
	
	public boolean resolve(String staffId, String staffMessage)
	{
		this.staffId = staffId;
		this.staffMessage = staffMessage;
		this.status = TicketStatus.Resolved;
		
		return true;
	}
}
