package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

import java.awt.TextArea;


@SuppressWarnings("serial")
public class GUIAccountStaffProcessTicketsSingle extends JPanel {
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!answer.getText().equals("")) {
				if(processCallback.onProcessTicket(answer.getText())) {
					error.setText("");
				} else {
					error.setText("Media not added.");
				}
			} else {
				error.setText("Empty fields not allowed.");
			}
		}
	}
	
	public interface IProcessTicketCallback{
		public boolean onProcessTicket(String answer);
	}
	
	private JLabel processtickets;
	private JLabel name;
	private JLabel date;
	private JEditorPane userMessage;
	private JEditorPane answer;
	
	private JLabel error;
	private IProcessTicketCallback processCallback;
	
	
	public GUIAccountStaffProcessTicketsSingle(IProcessTicketCallback callback) {
		setBounds(0,0,654,471);
		setLayout(null);
		processCallback = callback;
  
		processtickets = new JLabel("Process Tickets");
		processtickets.setFont(new Font("Tahoma", Font.PLAIN, 15));
		processtickets.setBounds(10, 10, 150, 25);
		add(processtickets);
		
		name = new JLabel("Rasmus.a-91@hotmail.com");
		name.setBounds(10, 35, 500, 25);
		add(name);
		
		date = new JLabel("2015-05-31 01:10");
		date.setBounds(10, 50, 500, 25);
		add(date);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);
		
		userMessage = new JEditorPane();
		userMessage.setBounds(10, 71, 634, 155);
		userMessage.setBorder(BorderFactory.createTitledBorder(border, "Message"));
		userMessage.setBackground(new Color(238,238,238));
		userMessage.setEditable(false);
		userMessage.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In ut fringilla justo. Nam egestas mauris quis tortor aliquet sagittis. Donec vestibulum iaculis luctus. Nullam pellentesque euismod velit, nec interdum lacus porttitor sit amet. Vivamus sagittis sapien fermentum auctor vestibulum. Nulla tincidunt, tellus sagittis mollis maximus, lacus velit facilisis mauris, vitae ultrices sapien magna aliquam nisl. Curabitur enim metus, dapibus quis aliquet id, elementum non dolor. Aliquam mollis risus metus. Vestibulum id consectetur odio, a suscipit nisl. Pellentesque a egestas lectus. Integer rhoncus tempus diam id dignissim.");
		add(userMessage);
		
		answer = new JEditorPane();
		answer.setBounds(10, 237, 634, 182);
		answer.setBorder(BorderFactory.createTitledBorder(border, "Answer"));
		answer.setBackground(new Color(238,238,238));
		add(answer);
  
		SubmitListener submitListener = new SubmitListener();
  
		JButton submit = new JButton("Submit");
		submit.setBounds(530, 430, 112, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
		error = new JLabel();
		error.setForeground(Color.RED);
		error.setBounds(10, 430, 250, 25);
		add(error);
		
	}
}
