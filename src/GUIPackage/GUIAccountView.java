package GUIPackage;
import java.util.HashMap;

import javax.swing.JPanel;

import MediaPackage.MediaStatus;
import MediaPackage.MediaType;
import TicketPackage.Ticket;
import UserPackage.User;
import DefaultPackage.IObserver;
import DefaultPackage.Observable;


public class GUIAccountView extends JPanel implements IObserver {
	
	public interface IAccountView {
		public void onProcessTicket(Ticket ticket, String answer);
		public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type);
		public boolean onAddBalance(float amount, String[] credentials);
	}
	
	private GUIAccountCustomer customerView;
	private GUIAccountStaff staffView;
	private JPanel contentView;
	private User user;
	private IAccountView accountCallback;
	
	public GUIAccountView(HashMap<String, Observable> observableList, IAccountView callback) {
		setBounds(0,0,860,550);
		setLayout(null);
		
		accountCallback = callback;
		
		staffView = new GUIAccountStaff(observableList, new GUIAccountStaff.IAccountStaffCallback() {
			
			@Override
			public void onProcessTicket(Ticket ticket, String answer) {
				accountCallback.onProcessTicket(ticket, answer);
			}

			@Override
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type) {
				return accountCallback.onAddMedia(name, price, amount, status, type);
			}
		});
		customerView = new GUIAccountCustomer(observableList, new GUIAccountCustomer.IAccountCustomerCallback() {
			
			@Override
			public boolean onAddBalance(float amount, String[] credentials) {
				return accountCallback.onAddBalance(amount, credentials);
			}
		});
		
		observableList.get("userHandler").addObserver(this);
	}
	
	private void changeView(JPanel newView)
	{
		if(contentView != null) {
			remove(contentView);
		}
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}

	@Override
	public void Update(Object object) {
		user = (User) object;
		if(user != null) {
			if(user.isStaff()) {
				changeView(staffView);
			} else {
				changeView(customerView);
			}
		}
	}

}
