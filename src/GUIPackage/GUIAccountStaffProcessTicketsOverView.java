package GUIPackage;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class GUIAccountStaffProcessTicketsOverView extends JPanel {

	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() == 2) {
				System.out.println(ticketList.getSelectedValue());
				selectCallback.onSelectCallback(ticketList.getSelectedValue());
			}
		}
	}
	
	public interface IOnSelectCallback {
		public void onSelectCallback(String id);
	}
	
	private JLabel processtickets;
	
	private JScrollPane scroll;
	private JList<String> ticketList;
	private Vector<String> ticketData;
	
	private IOnSelectCallback selectCallback;
	
	public GUIAccountStaffProcessTicketsOverView(IOnSelectCallback callback) {
		selectCallback = callback;
		setBounds(0,0,654, 471);
		setLayout(null);
		
		processtickets = new JLabel("Process Tickets");
		processtickets.setFont(new Font("Tahoma", Font.PLAIN, 15));
		processtickets.setBounds(10, 10, 150, 25);
		add(processtickets);
		
		ticketData = new Vector<String>();
		ticketData.add("SubmittedTicketName");
		ticketData.add("SubmittedTicketName2");
		
		ticketList = new JList<String>();
		ticketList.setListData(ticketData);
		ticketList.addMouseListener(new LabelMouseAdapter());
		
		scroll = new JScrollPane(ticketList);
		scroll.setBounds(10, 50, 633, 410);
		add(scroll);
	}
	


}
