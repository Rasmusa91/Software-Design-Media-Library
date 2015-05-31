package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


@SuppressWarnings("serial")
public class GUILogin extends JPanel {
	
	public interface ILoginCallback {
		public boolean login(String name, String pw);
	}
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean result = callback.login(name.getText(), String.valueOf(pw.getPassword()));
			if(!result) {
				error.setText("Wrong name or password");
			}
		}

	}
	
	private JPanel toppanel;
	private JLabel login;
	
	private JTextField name;
	private JPasswordField pw;
	
	private ILoginCallback callback;
	private JLabel error;
	
	public GUILogin(ILoginCallback callback) {
		setBounds(0, 0, 860, 561);
		setLayout(null);
		this.callback = callback;
		
		toppanel = new JPanel();
		toppanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		toppanel.setBounds(0, 0, 860, 40);
		toppanel.setLayout(null);
		add(toppanel);
		
		login = new JLabel("Login");
		login.setBounds(10, 5, 600, 30);
		login.setFont(new Font("Tahoma", Font.PLAIN, 20));
		toppanel.add(login);
		
		LineBorder border = new LineBorder ( Color.BLACK, 1);

		name = new JTextField();
		name.setColumns(10);
		name.setBackground(new Color(238,238,238));
		name.setBorder(BorderFactory.createTitledBorder(border, "Name"));
		name.setBounds(322, 160, 200, 40);
		add(name);
		
		pw = new JPasswordField();
		pw.setColumns(10);
		pw.setBackground(new Color(238,238,238));
		pw.setBorder(BorderFactory.createTitledBorder(border, "Password"));
		pw.setBounds(322, 210, 200, 40);
		add(pw);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submit = new JButton("Login");
		submit.setBounds(420, 265, 100, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
		error = new JLabel();
		error.setBounds(355, 300, 150, 30);
		error.setForeground(Color.RED);
		add(error);
	}

}
