package TicketPackage;

import java.util.ArrayList;
import java.util.HashMap;

import DefaultPackage.DatabaseHandler;

public class TicketHandlerStateStaff extends TicketHandlerState 
{
	public TicketHandlerStateStaff(ITicketHandlerStateChangeCallback stateChangeCallback) 
	{
		super(stateChangeCallback);
	}

	@Override
	protected void initialize()
	{
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Tickets");	
		initializeTickets(result);
	}
	
	@Override
	public boolean resolveTicket(Ticket ticket, String response)
	{
		ticket.resolve(user.getId(), response);
		
		DatabaseHandler.executeQuery("UPDATE Tickets "
				+ "SET staffId = '" + user.getId() + "', staffMessage = '" + response + "' "
				+ "WHERE id = '" + ticket.getId() + "'");
		
		return true;
	}	
}
