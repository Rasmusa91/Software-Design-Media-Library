package GUIPackage;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class GUIAccountCustomerQueuedMediaView extends JPanel {

	
	private JLabel queued;
	
	private JScrollPane scroll;
	private JList<String> queuedList;
	private Vector<String> queuedData;
	
	public GUIAccountCustomerQueuedMediaView() {
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		queued = new JLabel("Queued Media");
		queued.setFont(new Font("Tahoma", Font.PLAIN, 15));
		queued.setBounds(10, 10, 150, 25);
		add(queued);
		
		queuedData = new Vector<String>();
		queuedData.add("QueuedName");
		
		queuedList = new JList<String>();
		queuedList.setListData(queuedData);
		
		scroll = new JScrollPane(queuedList);
		scroll.setBounds(10, 50, 635, 410);
 		add(scroll);

	}

}
