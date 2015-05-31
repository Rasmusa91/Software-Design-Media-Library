package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class GUIAccountStaffAddMediaView extends JPanel {
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!type.getText().equals("") && 
					!name.getText().equals("") && 
					!price.getText().equals("") &&
					!amount.getText().equals("")) {
				try {
					Float.valueOf(price.getText());
					Float.valueOf(amount.getText());
					
					String[] amedia = {name.getText() ,price.getText(), amount.getText(), type.getText()};
					if(addMediaCallback.onAddMedia(amedia)) {
						error.setText("");
					} else {
						error.setText("Media not added.");
					}
				} catch(Exception e1) {
					error.setText("Price and amount needs to be numbers.");
				}
				
			} else {
				error.setText("Empty fields not allowed.");
			}
		}
	}
	
	public interface IAddMediaCallback{
		public boolean onAddMedia(String[] credentials);
	}
	
	private JLabel addmedia;
	
	private JTextField type;
	private JTextField name;
	private JTextField price;
	private JTextField amount;
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
		
		type = new JTextField();
		type.setColumns(10);
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
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submit = new JButton("Submit");
		submit.setBounds(542, 220, 100, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(10, 220, 250, 25);
		add(error);
	}
}
