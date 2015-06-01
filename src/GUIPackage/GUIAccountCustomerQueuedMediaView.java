package GUIPackage;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DefaultPackage.IObserver;
import DefaultPackage.Observable;
import MediaPackage.Media;
import UserPackage.User;


public class GUIAccountCustomerQueuedMediaView extends JPanel  {

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
		
		queuedList = new JList<String>();
		queuedList.setListData(queuedData);
		
		scroll = new JScrollPane(queuedList);
		scroll.setBounds(10, 50, 635, 410);
 		add(scroll);
	}
	
	public void updateMedia(ArrayList<Media> media, User user) {
		if(user != null) {
			queuedData.clear();
			for (Media m : media) {
				if(m.getUserQueuePosition(user.getId()) != -1) {
					queuedData.add(m.getName());
				}
			}
		}
	}
}
