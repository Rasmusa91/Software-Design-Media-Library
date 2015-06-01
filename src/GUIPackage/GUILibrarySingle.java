package GUIPackage;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MediaPackage.Media;


@SuppressWarnings("serial")
public class GUILibrarySingle extends JPanel {
	
	public class RentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			rentCallback.onRent(thisMedia);
		}
		
	}
	
	public interface IOnRentCallback {
		public boolean onRent(Media media);
	}
	
	private JLabel name;
	private JLabel status;
	private JLabel amount;
	private JLabel price;
	private JLabel type;
	
	private Media thisMedia;
	
	private IOnRentCallback rentCallback;
	
	public GUILibrarySingle(IOnRentCallback callback, Media media) {
		setBounds(0, 0, 844, 250);
		setLayout(null);
		rentCallback = callback;
		thisMedia = media;

		name = new JLabel(media.getName());
		name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name.setBounds(0, 0, 600, 30);
		add(name);
		
		status = new JLabel(media.getStatus().toString());
		status.setBounds(0, 40, 200, 15);
		add(status);
		
		amount = new JLabel(String.valueOf(media.getAmount()));
		amount.setBounds(0, 65, 200, 15);
		add(amount);
		
		price = new JLabel(String.valueOf(media.getPrice()));
		price.setBounds(0, 90, 200, 15);
		add(price);
		
		type = new JLabel(media.getType().toString());
		type.setBounds(0, 115, 200, 15);
		add(type);
		
		RentListener rentListener = new RentListener();
		if(media.getAmount() > 0) {
			JButton rent = new JButton("Rent "+ media.getPrice() +"kr / 2weeks");
			rent.setBounds(0, 150, 175, 30);
			rent.addActionListener(rentListener);
			add(rent);
		} else {
			JButton queue = new JButton("Queue for this media");
			queue.setBounds(0, 150, 175, 30);
			queue.addActionListener(rentListener);
			add(queue);
		}
		
	}
}
