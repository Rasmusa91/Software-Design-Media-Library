package GUIPackage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import TicketPackage.Ticket;
import DefaultPackage.IObserver;
import DefaultPackage.Observable;

@SuppressWarnings("serial")
public class GUIAccountStaffProcessTicketsView extends JPanel implements IObserver {

	public interface IProcessTicketCallback{
		public void onProcessTicket(Ticket ticket, String answer);
	}
	
	private IProcessTicketCallback processCallback;
	
	private JPanel contentView;
	private GUIAccountStaffProcessTicketsOverView listView;
	private ArrayList<Ticket> tickets;
	private HashMap<String, Observable> observableList;

	public GUIAccountStaffProcessTicketsView(IProcessTicketCallback callback, HashMap<String, Observable> observableList) {
		processCallback = callback;
		setBounds(190,40,654,471);
		setLayout(null);
		this.observableList = observableList;
		
		listView = new GUIAccountStaffProcessTicketsOverView(new GUIAccountStaffProcessTicketsOverView.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback(String id) {
				for (final Ticket ticket : tickets) {
					if(("Ticket: " + ticket.getId()).equals(id)) {
						changeView(new GUIAccountStaffProcessTicketsSingle(new GUIAccountStaffProcessTicketsSingle.IProcessTicketCallback() {
							
							@Override
							public boolean onProcessTicket(String answer) {
								processCallback.onProcessTicket(ticket, answer);
								changeView(listView);
								return true;
							}
						}, ticket));
						break;
					}
				}
				
			}
		}, tickets);
		
		observableList.get("ticketHandler").addObserver(this);
		
		contentView = listView;
		add(contentView);
	}
	
	private void changeView(JPanel newView)
	{
		
		remove(contentView);
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}
	
	public void unsubscribe() {
		observableList.get("ticketHandler").removeObserver(this);
	}

	@Override
	public void Update(Object object) {
		tickets = (ArrayList<Ticket>) object;
		if(tickets != null) {
			listView.updateTickets(tickets);
		}
		
		//System.out.println(tickets.toString());
	}
		
}


