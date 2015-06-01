package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import MediaPackage.MediaStatus;
import MediaPackage.MediaType;


@SuppressWarnings("serial")
public class GUIAccountStaffAddMediaView extends JPanel {
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!name.getText().equals("") && 
					!price.getText().equals("") &&
					!amount.getText().equals("")) {
				try {
					MediaStatus status;
					if(statusbox.isSelected()) {
						status = MediaStatus.InStock;
					} else {
						status = MediaStatus.UnavailableIndefinitive;
					}
					
					if(addMediaCallback.onAddMedia(name.getText() ,Float.valueOf(price.getText()), Integer.valueOf(amount.getText()), status, (MediaType) type.getSelectedItem())) {
						error.setText("Media has been added");
						name.setText("");
						price.setText("");
						amount.setText("");
						error.setForeground(Color.GREEN);
					} else {
						error.setText("Media not added.");
						error.setForeground(Color.RED);
					}
				} catch(Exception e1) {
					error.setText("Price and amount needs to be numbers.");
					error.setForeground(Color.RED);
				}
				
			} else {
				error.setText("Empty fields not allowed.");
				error.setForeground(Color.RED);
			}
		}
	}
	
	public interface IAddMediaCallback{
		public boolean onAddMedia(String name, float price, int amount, MediaStatus status , MediaType type);
	}
	
	private JLabel addmedia;
	
	private JComboBox<MediaType> type;
	
	private JTextField name;
	private JTextField price;
	private JTextField amount;
	private JCheckBox statusbox;
	private IAddMediaCallback addMediaCallback;
	
	private JLabel error;
	
	
	public GUIAccountStaffAddMediaView(IAddMediaCallback callback) {
		setBounds(0,0,654,471);
		setLayout(null);
		addMediaCallback = callback;
		
		addmedia = new JLabel("Add Media Item");
		addmedia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addmedia.setBounds(10, 10, 150, 25);
		add(addmedia);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);
		
		type = new JComboBox<MediaType>();
		for (MediaType mediaType : MediaType.values()) {
			 type.addItem(mediaType);
		}
		type.setBounds(10, 50, 634, 40);
		type.setBackground(new Color(238,238,238));
		type.setBorder(BorderFactory.createTitledBorder(border, "Type"));
		add(type);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBackground(new Color(238,238,238));
		name.setBorder(BorderFactory.createTitledBorder(border, "Name"));
		name.setBounds(10, 90, 634, 40);
		add(name);
		
		price = new JTextField();
		price.setColumns(10);
		price.setBackground(new Color(238,238,238));
		price.setBorder(BorderFactory.createTitledBorder(border, "Price"));
		price.setBounds(10, 130, 634, 40);
		add(price);
		
		amount = new JTextField();
		amount.setColumns(10);
		amount.setBackground(new Color(238,238,238));
		amount.setBorder(BorderFactory.createTitledBorder(border, "Amount"));
		amount.setBounds(10, 170, 634, 40);
		add(amount);
		
		statusbox = new JCheckBox("Available");
		statusbox.setBounds(10, 220, 100, 40);
		statusbox.setSelected(true);
		add(statusbox);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submit = new JButton("Submit");
		submit.setBounds(542, 220, 100, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(10, 270, 250, 25);
		add(error);
	}
}
