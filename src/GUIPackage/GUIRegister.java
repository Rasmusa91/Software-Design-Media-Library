package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


@SuppressWarnings("serial")
public class GUIRegister extends JPanel {
	
	public interface IRegisterCallback {
		public boolean register(String name, String pw);
	}
	
	private class SubmitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(name.getText().equals("") ||
					String.valueOf(pw.getPassword()).equals("")) {
				error.setText("Empty fields not allowed.");
			} else if(!String.valueOf(pw2.getPassword()).equals(String.valueOf(pw.getPassword()))) {
				error.setText("Passwords not matching.");
			} else {
				boolean result = callback.register(name.getText(), String.valueOf(pw.getPassword()));
				if(!result) {
					error.setText("Account already exists.");
				} else {
					error.setText("");
				}
			}
		}

	}
	
	private JPanel toppanel;
	private JLabel login;
	
	private JTextField name;
	private JPasswordField pw;
	private JPasswordField pw2;
	private JLabel error;
	
	private IRegisterCallback callback;
	
	public GUIRegister(IRegisterCallback callback) {
		setBounds(0, 0, 860, 561);
		setLayout(null);
		this.callback = callback;
		
		toppanel = new JPanel();
		toppanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		toppanel.setBounds(0, 0, 860, 40);
		toppanel.setLayout(null);
		add(toppanel);
		
		login = new JLabel("Signup");
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
		
		pw2 = new JPasswordField();
		pw2.setColumns(10);
		pw2.setBackground(new Color(238,238,238));
		pw2.setBorder(BorderFactory.createTitledBorder(border, "Repeat Password"));
		pw2.setBounds(322, 260, 200, 40);
		add(pw2);
		
		SubmitListener submitListener = new SubmitListener();
		
		JButton submit = new JButton("Signup");
		submit.setBounds(420, 315, 100, 30);
		submit.addActionListener(submitListener);
		add(submit);
		
		error = new JLabel();
		error.setBounds(355, 350, 200, 30);
		error.setForeground(Color.RED);
		add(error);
	}

}
