package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;


public class GUIAccountCustomerSupportView extends JPanel {

	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			callback.onAddTicket(ticket.getText());
			
		}
		
	}
	
	public interface IAddSupportTicket{
		public void onAddTicket(String ticket);
		
	}
	
	private IAddSupportTicket callback;
	
	private JLabel support;
	private JTextArea ticket;
	
	public GUIAccountCustomerSupportView(IAddSupportTicket callback) {
		
		this.callback = callback;
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		support = new JLabel("Support");
		support.setFont(new Font("Tahoma", Font.PLAIN, 15));
		support.setBounds(10, 10, 150, 25);
		add(support);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);
		
		ticket = new JTextArea();
		ticket.setBounds(10, 50, 634, 345);
		ticket.setBorder(BorderFactory.createTitledBorder(border, "Ticket"));
		ticket.setBackground(new Color(238,238,238));
		add(ticket);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submit = new JButton("Submit");
		submit.setBounds(542, 405, 100, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
	}
}
