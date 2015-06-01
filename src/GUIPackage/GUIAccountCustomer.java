package GUIPackage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import MediaPackage.Media;
import UserPackage.Customer;
import UserPackage.User;
import DefaultPackage.IObserver;
import DefaultPackage.Observable;


public class GUIAccountCustomer extends JPanel implements IObserver {
	
	public interface IAccountCustomerCallback {
		public boolean onAddBalance(float amount, String[] credentials);
	}
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(((JLabel)arg0.getSource()).getText() == "<html><u>Rented Media</u></html>")
			{
				changeView(rentedView);
			}
			else if(((JLabel)arg0.getSource()).getText() == "<html><u>Queued Media</u></html>")
			{
				changeView(queuedView);
			}
			else if(((JLabel)arg0.getSource()).getText() == "<html><u>Add Balance</u></html>")
			{
				changeView(new GUIAccountCustomerAddBalanceView(balanceCallback));
			}
			else if(((JLabel)arg0.getSource()).getText() == "<html><u>Transaction History</u></html>")
			{
				changeView(transactionView);
			}
			else if(((JLabel)arg0.getSource()).getText() == "<html><u>Support</u></html>")
			{
				changeView(supportView);
			}
		}
	}
	
	private JPanel toppanel;
	private JLabel account;
	private JLabel balance;
	
	private JPanel sidebarpanel;
	private JLabel rentedMedia;
	private JLabel queuedMedia;
	private JLabel addBalance;
	private JLabel transactionHistory;
	private JLabel support;
	
	private JPanel contentView;
	
	private GUIAccountCustomerRentedMediaView rentedView;
	private GUIAccountCustomerQueuedMediaView queuedView;
	private GUIAccountCustomerTransactionHistoryView transactionView;
	private GUIAccountCustomerSupportView supportView;
	private GUIAccountCustomerAddBalanceView.IAddBalanceCallback balanceCallback;
	private IAccountCustomerCallback customerCallback;
	
	
	private Customer user;
	private ArrayList<Media> media;
	
	public GUIAccountCustomer(HashMap<String, Observable> observableList, IAccountCustomerCallback callback) {
		setBounds(0,0,860,550);
		setLayout(null);
		
		customerCallback = callback;
		
		toppanel = new JPanel();
		toppanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		toppanel.setBounds(0, 0, 860, 40);
		toppanel.setLayout(null);
		add(toppanel);
		
		account = new JLabel("Account Management");
		account.setBounds(10, 5, 600, 30);
		account.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toppanel.add(account);
		
		balance = new JLabel("Balance: ");
		balance.setBounds(670, 5, 162, 30);
		balance.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toppanel.add(balance);
		
		sidebarpanel = new JPanel();
		sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		sidebarpanel.setBounds(0, 40, 190, 472);
		sidebarpanel.setLayout(null);
		add(sidebarpanel);
		
		LabelMouseAdapter mouseAdapter = new LabelMouseAdapter();
		
		//Sidebar For Account Staff
		rentedMedia = new JLabel("<html><u>Rented Media</u></html>");
		rentedMedia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rentedMedia.addMouseListener(mouseAdapter);
		rentedMedia.setBounds(10, 10, 150, 15);
		sidebarpanel.add(rentedMedia);
		
		queuedMedia = new JLabel("<html><u>Queued Media</u></html>");
		queuedMedia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		queuedMedia.addMouseListener(mouseAdapter);
		queuedMedia.setBounds(10, 30, 150, 15);
		sidebarpanel.add(queuedMedia);
		
		addBalance = new JLabel("<html><u>Add Balance</u></html>");
		addBalance.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addBalance.addMouseListener(mouseAdapter);
		addBalance.setBounds(10, 50, 150, 15);
		sidebarpanel.add(addBalance);
		
		transactionHistory = new JLabel("<html><u>Transaction History</u></html>");
		transactionHistory.setCursor(new Cursor(Cursor.HAND_CURSOR));
		transactionHistory.addMouseListener(mouseAdapter);
		transactionHistory.setBounds(10, 70, 150, 15);
		sidebarpanel.add(transactionHistory);
		
		support = new JLabel("<html><u>Support</u></html>");
		support.setCursor(new Cursor(Cursor.HAND_CURSOR));
		support.addMouseListener(mouseAdapter);
		support.setBounds(10, 90, 150, 15);
		sidebarpanel.add(support);
		
		////////////***VIEWS***//////////////////
		rentedView = new GUIAccountCustomerRentedMediaView();
		rentedView.setLocation(190, 40);
		
		queuedView = new GUIAccountCustomerQueuedMediaView();
		queuedView.setLocation(190, 40);
		
		balanceCallback = new GUIAccountCustomerAddBalanceView.IAddBalanceCallback() {
			
			@Override
			public boolean onAddAmount(float amount, String[] credentials) {
				boolean result = customerCallback.onAddBalance(amount, credentials);
				if(result) {
					changeView(new GUIAccountCustomerAddBalanceView(balanceCallback));
				}
				return result;
			}
		};
		
		transactionView = new GUIAccountCustomerTransactionHistoryView();
		transactionView.setLocation(190, 40);
		
		supportView = new GUIAccountCustomerSupportView(new GUIAccountCustomerSupportView.IAddSupportTicket() {
			
			@Override
			public void onAddTicket(String ticket) {
				System.out.println(ticket);
				
			}
		});
		supportView.setLocation(190, 40);
		
		contentView = rentedView;
		add(contentView);
		
		observableList.get("userHandler").addObserver(this);
		observableList.get("mediaHandler").addObserver(this);
	}
	
	private void changeView(JPanel newView)
	{
		remove(contentView);
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}

	@Override
	public void Update(Object object) {
		if(object instanceof Customer) {
			user = (Customer) object;
			balance.setText("Balance: " + String.valueOf(user.getBalance()) + ":-");
			queuedView.updateMedia(media, user);
			rentedView.updateMedia(media, user);
		} else if(object instanceof ArrayList){
			media = (ArrayList<Media>) object;
			queuedView.updateMedia(media, user);
			rentedView.updateMedia(media, user);
		}
	}

}
