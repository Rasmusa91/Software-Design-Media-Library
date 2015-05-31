package GUIPackage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class GUIAccountStaff extends JPanel {
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(((JLabel)arg0.getSource()).getText() == "<html><u>Add Media</u></html>") {
				changeView(addmediaView);
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Financial Statistics</u></html>") {
				changeView(statsView);
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Process Tickets</u></html>") {
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
	
	public GUIAccountStaff() {
		setBounds(0, 0, 844, 561);
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
			public boolean onAddMedia(String[] credentials) {
				System.out.println("TESTADDINGMEDIA");
				return true;
			}
		});
		addmediaView.setLocation(190, 40);
		
		statsView = new GUIAccountStaffFinancialStatisticsView();
		statsView.setLocation(190, 40);
		
		processView = new GUIAccountStaffProcessTicketsView(new GUIAccountStaffProcessTicketsView.IProcessTicketCallback() {
			
			@Override
			public void onProcessTicket() {
				// TODO Auto-generated method stub
				System.out.println("TestProcessViewINGUIAccountStaff");
				
			}
		});
		processView.setLocation(190, 40);
		
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
