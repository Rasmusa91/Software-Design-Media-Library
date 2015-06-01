package TicketPackage;

import java.util.ArrayList;
import java.util.HashMap;

import DefaultPackage.*;
import UserPackage.*;

public abstract class TicketHandlerState extends Observable implements IObserver
{
	protected ArrayList<Ticket> tickets;
	private ITicketHandlerStateChangeCallback stateChangeCallback;
	protected User user;	
	
	public TicketHandlerState(ITicketHandlerStateChangeCallback stateChangeCallback)
	{
		this.stateChangeCallback = stateChangeCallback;
		tickets = new ArrayList<Ticket>();		
	}
	
	protected void initialize()
	{
	}
	
	protected void initializeTickets(ArrayList<HashMap<String, Object>> ticketList)
	{
		for (HashMap<String, Object> hashMap : ticketList) 
		{
			String id = hashMap.get("id").toString();
			String customerId = hashMap.get("customerId").toString();
			String customerMessage = hashMap.get("customerMessage").toString();
			String staffId = (hashMap.get("staffId") != null ? hashMap.get("staffId").toString() : null);
			String staffMessage = (hashMap.get("staffMessage") != null ? hashMap.get("staffMessage").toString() : null);
			
			Ticket newTicket = new Ticket(id, customerId, customerMessage);
			
			if(staffId != null) {
				newTicket.resolve(staffId, staffMessage);
			}
			
			tickets.add(newTicket);
		}		
	}
	
	public boolean addTicket(String message)
	{
		return false;
	}
	
	public boolean resolveTicket(Ticket ticket, String response)
	{
		return false;
	}
	
	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
		observer.Update(tickets);
	}
	
	@Override
	public void Update(Object object) 
	{
		user = (User) object;
		
		TicketHandlerState handler = null;
		if(user == null) {
			handler = new TicketHandlerStateNone(stateChangeCallback);
		}
		else if(user.isStaff()) {
			handler = new TicketHandlerStateStaff(stateChangeCallback);			
		}
		else {
			handler = new TicketHandlerStateCustomer(stateChangeCallback);
		}

		handler.setUser(user);
		handler.initialize();
		stateChangeCallback.stateChange(handler);
	}
	
	private void setUser(User user)
	{
		this.user = user;
	}	
}
