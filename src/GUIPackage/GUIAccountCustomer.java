package GUIPackage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class GUIAccountCustomer extends JPanel {
	
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
	
	public GUIAccountCustomer() {

		setBounds(0,0,844,561);
		setLayout(null);
		
		toppanel = new JPanel();
		toppanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		toppanel.setBounds(0, 0, 844, 40);
		toppanel.setLayout(null);
		add(toppanel);
		
		account = new JLabel("Account Management");
		account.setBounds(10, 5, 600, 30);
		account.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toppanel.add(account);
		
		balance = new JLabel("Balance: 500:-");
		balance.setBounds(692, 5, 142, 30);
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
				System.out.println(amount + " " + credentials[0] + " " + credentials[1] + " " + credentials[2] + " " + credentials[3] + " " + 
						credentials[4] + " " + credentials[5]);
				return true;
				
			}
		};
		
		GUIAccountCustomerAddBalanceView addBalanceView = new GUIAccountCustomerAddBalanceView(balanceCallback);
		
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
		
		
	}
	
	private void changeView(JPanel newView)
	{
		remove(contentView);
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}

}
