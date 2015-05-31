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


public class GUIAccountCustomerAddCredentials extends JPanel {

	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!name.getText().equals("") && 
					!lastName.getText().equals("") && 
					!cardNr.getText().equals("") && 
					!year.getText().equals("") &&
					!month.getText().equals("") &&
					!cvc.getText().equals("")) {
				String[] credentials = {name.getText() ,lastName.getText(), cardNr.getText(), year.getText(), month.getText(), cvc.getText()};
				if(callback.onAddCredentials(credentials)) {
					error.setText("");
				} else {
					error.setText("Transfer failed.");
				}
			} else {
				error.setText("Empty fields not allowed.");
			}
			
		}
		
	}
	
	public interface IAddCredentialsCallback{
		public boolean onAddCredentials(String[] credentials);
	}
	
	private IAddCredentialsCallback callback;
	
	private JLabel addBalance;
	
	private JTextField name;
	private JTextField lastName;
	private JTextField cardNr;
	private JTextField year;
	private JTextField month;
	private JTextField cvc;
	
	private JLabel error;
	
	public GUIAccountCustomerAddCredentials(IAddCredentialsCallback callback) {
			
		this.callback = callback;
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		addBalance = new JLabel("Add Balance");
		addBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addBalance.setBounds(10, 10, 150, 25);
		add(addBalance);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);
		
		name = new JTextField();
		name.setBounds(10, 50, 312, 40);
		name.setColumns(10);
		name.setBackground(new Color(238,238,238));
		name.setBorder(BorderFactory.createTitledBorder(border, "Name"));
		add(name);
		
		lastName = new JTextField();
		lastName.setBounds(332, 50, 312, 40);
		lastName.setColumns(10);
		lastName.setBackground(new Color(238,238,238));
		lastName.setBorder(BorderFactory.createTitledBorder(border, "Last Name"));
		add(lastName);
		
		cardNr = new JTextField();
		cardNr.setBounds(10, 90, 634, 40);
		cardNr.setColumns(10);
		cardNr.setBackground(new Color(238,238,238));
		cardNr.setBorder(BorderFactory.createTitledBorder(border, "Card Number"));
		add(cardNr);
		
		year = new JTextField();
		year.setBounds(10, 130, 204, 40);
		year.setColumns(10);
		year.setBackground(new Color(238,238,238));
		year.setBorder(BorderFactory.createTitledBorder(border, "Year"));
		add(year);
		
		month = new JTextField();
		month.setBounds(225, 130, 204, 40);
		month.setColumns(10);
		month.setBackground(new Color(238,238,238));
		month.setBorder(BorderFactory.createTitledBorder(border, "Month"));
		add(month);
		
		cvc = new JTextField();
		cvc.setBounds(440, 130, 204, 40);
		cvc.setColumns(10);
		cvc.setBackground(new Color(238,238,238));
		cvc.setBorder(BorderFactory.createTitledBorder(border, "cvc"));
		add(cvc);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(542, 180, 100, 30);
		submitButton.addActionListener(submitListener);
		add(submitButton);
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(10, 210, 150, 25);
		add(error);
		
	}

}
