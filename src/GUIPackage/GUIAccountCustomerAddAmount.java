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


public class GUIAccountCustomerAddAmount extends JPanel {

	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(!Float.valueOf(amount.getText()).equals("")) {
					callback.onAddAmount(Float.valueOf(amount.getText()));
				}
			} catch(Exception e1) {
				error.setText("Must be a number.");
			}
			
		}
		
	}
	
	public interface IAddAmountCallback{
		public void onAddAmount(float amount);
		
	}
	
	private IAddAmountCallback callback;
	private JLabel addBalance;
	private JTextField amount;
	private JLabel error;

	public GUIAccountCustomerAddAmount(IAddAmountCallback callback) {
			
		this.callback = callback;
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		addBalance = new JLabel("Add Balance");
		addBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addBalance.setBounds(10, 10, 150, 25);
		add(addBalance);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);
		
		amount = new JTextField();
		amount.setBounds(10, 50, 634, 40);
		amount.setColumns(10);
		amount.setBackground(new Color(238,238,238));
		amount.setBorder(BorderFactory.createTitledBorder(border, "Amount"));
		add(amount);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBounds(542, 100, 100, 30);
		submitButton.addActionListener(submitListener);
		add(submitButton);
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(10, 130, 150, 25);
		add(error);
			
	}

}
