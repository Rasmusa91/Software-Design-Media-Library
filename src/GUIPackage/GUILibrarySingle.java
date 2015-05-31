package GUIPackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUILibrarySingle extends JPanel {
	
	public class RentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rentCallback.onRent();
		}
		
	}
	
	public interface IOnRentCallback {
		public boolean onRent();
	}
	
	private JLabel name;
	private JLabel status;
	private JLabel amount;
	private JLabel price;
	private JLabel type;
	
	private IOnRentCallback rentCallback;
	
	public GUILibrarySingle(IOnRentCallback callback) {
		setBounds(0, 0, 844, 250);
		setLayout(null);
		rentCallback = callback;
		
		name = new JLabel("MediaName");
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(0, 0, 600, 30);
		add(name);
		
		status = new JLabel("Status: InStock");
		status.setBounds(0, 40, 200, 15);
		add(status);
		
		amount = new JLabel("Amount: 20");
		amount.setBounds(0, 65, 200, 15);
		add(amount);
		
		price = new JLabel("Price: 25kr");
		price.setBounds(0, 90, 200, 15);
		add(price);
		
		type = new JLabel("Type: EBook");
		type.setBounds(0, 115, 200, 15);
		add(type);
		
		JButton rent = new JButton("Rent 25kr/24h");
		rent.setBounds(0, 140, 150, 20);
		add(rent);
	}
}
