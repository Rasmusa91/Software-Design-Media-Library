package GUIPackage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import MediaPackage.MediaStatus;
import MediaPackage.MediaType;
import TicketPackage.Ticket;
import DefaultPackage.Observable;

@SuppressWarnings("serial")
public class GUIAccountStaff extends JPanel {
	
	public interface IAccountStaffCallback {
		public void onProcessTicket(Ticket ticket, String answer);
		public boolean onAddMedia(String name, float price, int amount, MediaStatus status , MediaType type);
	}
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(((JLabel)arg0.getSource()).getText() == "<html><u>Add Media</u></html>") {
				changeView(addmediaView);
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Financial Statistics</u></html>") {
				changeView(statsView);
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Process Tickets</u></html>") {
				processView.unsubscribe();
				processView = new GUIAccountStaffProcessTicketsView(processListCallback, observableList);
				changeView(processView);
			}
		}
	}
	
	private JPanel toppanel;
	private JLabel account;
	
	private JPanel sidebarpanel;
	private JLabel addMedia;
	private JLabel financial;
	private JLabel process;
	
	private JPanel contentView;
	private GUIAccountStaffAddMediaView addmediaView;
	private GUIAccountStaffFinancialStatisticsView statsView;
	private GUIAccountStaffProcessTicketsView processView;
	private GUIAccountStaffProcessTicketsView.IProcessTicketCallback processListCallback;
	private IAccountStaffCallback staffCallback;
	private HashMap<String, Observable> observableList;
	
	public GUIAccountStaff(HashMap<String, Observable> observableList, IAccountStaffCallback callback) {
		setBounds(0, 0, 844, 561);
		setLayout(null);
		staffCallback = callback;
		this.observableList = observableList;
		
		toppanel = new JPanel();
		toppanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		toppanel.setBounds(0, 0, 844, 40);
		toppanel.setLayout(null);
		add(toppanel);
		
		account = new JLabel("Account Management");
		account.setBounds(10, 5, 600, 30);
		account.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toppanel.add(account);
		
		sidebarpanel = new JPanel();
		sidebarpanel.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
		sidebarpanel.setBounds(0, 40, 190, 472);
		sidebarpanel.setLayout(null);
		add(sidebarpanel);
		
		LabelMouseAdapter mouseAdapter = new LabelMouseAdapter();
		
		addMedia = new JLabel("<html><u>Add Media</u></html>");
		addMedia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addMedia.addMouseListener(mouseAdapter);
		addMedia.setBounds(10, 10, 150, 15);
		sidebarpanel.add(addMedia);
		
		financial = new JLabel("<html><u>Financial Statistics</u></html>");
		financial.setCursor(new Cursor(Cursor.HAND_CURSOR));
		financial.addMouseListener(mouseAdapter);
		financial.setBounds(10, 30, 150, 15);
		sidebarpanel.add(financial);
		
		process = new JLabel("<html><u>Process Tickets</u></html>");
		process.setCursor(new Cursor(Cursor.HAND_CURSOR));
		process.addMouseListener(mouseAdapter);
		process.setBounds(10, 50, 150, 15);
		sidebarpanel.add(process);
		
		addmediaView = new GUIAccountStaffAddMediaView(new GUIAccountStaffAddMediaView.IAddMediaCallback() {
			
			@Override
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status , MediaType type) {
				return staffCallback.onAddMedia(name, price, amount, status, type);
			}
		});
		addmediaView.setLocation(190, 40);
		
		statsView = new GUIAccountStaffFinancialStatisticsView(observableList);
		statsView.setLocation(190, 40);
		
		processListCallback = new GUIAccountStaffProcessTicketsView.IProcessTicketCallback() {
			
			@Override
			public void onProcessTicket(Ticket ticket, String answer) {
				staffCallback.onProcessTicket(ticket, answer);
			}
		};
		
		processView = new GUIAccountStaffProcessTicketsView(processListCallback, observableList);
		
		
		contentView = addmediaView;
		add(contentView);
		
	}
	
	private void changeView(JPanel newView) {
		
		remove(contentView);
		contentView = newView;
		add(newView);
		revalidate();
		repaint();
	}

}
