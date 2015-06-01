package GUIPackage;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import MediaPackage.Media;
import UserPackage.Customer;
import UserPackage.RentedMedia;
import UserPackage.User;


public class GUIAccountCustomerRentedMediaView extends JPanel {
	
	private JLabel rented;
	
	private JScrollPane scroll;
	private JList<String> rentedList;
	private Vector<String> rentedData;
	
	public GUIAccountCustomerRentedMediaView() {

		setBounds(0,0,654,471);
		setLayout(null);
		
		rented = new JLabel("Rented Media");
		rented.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rented.setBounds(10, 10, 150, 25);
		add(rented);
		
		rentedData = new Vector<String>();
		rentedData.add("RentedName");
		
		rentedList = new JList<String>();
		rentedList.setListData(rentedData);
		
		scroll = new JScrollPane(rentedList);
		scroll.setBounds(10, 50, 635, 410);
 		add(scroll);
	}
	
	public void updateMedia(ArrayList<Media> media, Customer user) {
		if(user != null) {
			rentedData.clear();
			for (Media m : media) {
				for (RentedMedia m2 : user.getRentedMedia()) {
					if(m.getId().equals(m2.getMediaID())) {
						rentedData.add(m.getName());
					}
				}
			}
		}
	}

}
