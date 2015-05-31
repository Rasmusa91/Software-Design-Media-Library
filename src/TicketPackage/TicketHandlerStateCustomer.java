package TicketPackage;

import java.util.ArrayList;
import java.util.HashMap;

import DefaultPackage.DatabaseHandler;

public class TicketHandlerStateCustomer extends TicketHandlerState {

	public TicketHandlerStateCustomer(ITicketHandlerStateChangeCallback stateChangeCallback) 
	{
		super(stateChangeCallback);
	}

	@Override
	protected void initialize()
	{
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Tickets "
				+ "WHERE customerID = '" + user.getId() + "'");
		
		initializeTickets(result);
	}
	
	@Override
	public boolean addTicket(String message)
	{
		DatabaseHandler.executeQuery("INSERT INTO Tickets "
				+ "(customerId, customerMessage) "
				+ "VALUES('" + user.getId() + "', '" + message + "')");
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT MAX(id) AS LastID FROM Tickets");
		String lastID = result.get(0).get("LastID").toString();
		
		Ticket newTicket = new Ticket(lastID, user.getId(), message);
		tickets.add(newTicket);

		return true;
	}	
}
