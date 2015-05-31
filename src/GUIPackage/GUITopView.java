package GUIPackage;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class GUITopView extends JPanel {

	public interface ISelectedCallback {
		public void loginCallback();
		public void logoutCallback();
		public void registerCallback();
		public void libraryCallback();
		public void searchCallback(String searchterm);
	}
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(((JLabel)arg0.getSource()).getText() == "<html><u>Login</u></html>") {
				callback.loginCallback();
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Logout</u></html>") {
				callback.logoutCallback();
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Register</u></html>") {
				callback.registerCallback();
			} else if(((JLabel)arg0.getSource()).getText() == "<html><u>Library</u></html>") {
				callback.libraryCallback();
			}
		}
	}

	private class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			callback.searchCallback(searchField.getText());
		}
		
	}
	
	private JTextField searchField;
	private JLabel library;
	private JLabel label1;
	private JLabel label2;
	
	/*private JPanel contentView;
	private GUILogin loginView;
	private GUIRegister registerView;*/
	
	private ISelectedCallback callback;
	
	public GUITopView(ISelectedCallback callback) {
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		setBounds(0, 0, 860, 50);
		setLayout(null);
		
		this.callback = callback;
		
		searchField = new JTextField();
		searchField.setBounds(10, 11, 200, 28);
		searchField.setColumns(10);
		add(searchField);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(208, 11, 75, 27);
		btnNewButton.addActionListener(new SearchListener());
		add(btnNewButton);
		
		LabelMouseAdapter mouseAdapter = new LabelMouseAdapter();
		
		library = new JLabel("<html><u>Library</u></html>");
		library.setBounds(300, 18, 45, 14);
		library.setCursor(new Cursor(Cursor.HAND_CURSOR));
		library.addMouseListener(mouseAdapter);
		add(library);
		
		label1 = new JLabel("<html><u>Login</u></html>");
		label1.setBounds(720, 18, 45, 14);
		label1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));
		label1.addMouseListener(mouseAdapter);
		add(label1);
		
		label2 = new JLabel("<html><u>Register</u></html>");
		label2.setBounds(776, 18, 50, 14);
		label2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		label2.addMouseListener(mouseAdapter);
		add(label2);
			
	}
}
