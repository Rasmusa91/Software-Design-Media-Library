package GUIPackage;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


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

}
