package GUIPackage;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DefaultPackage.*;


public class GUI extends JFrame {

	private JPanel contentPane;
	private GUIMainContent mainView;
	private IGUICallback guiCallback;

	public GUI(IGUICallback callback, HashMap<String, Observable> observableList) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 600);
		guiCallback = callback;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		GUITopView topView = new GUITopView(new GUITopView.ISelectedCallback() {
			
			@Override
			public void registerCallback() {
				mainView.showRegister();
			}
			
			@Override
			public void logoutCallback() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void loginCallback() {
				mainView.showLogin();
			}

			@Override
			public void libraryCallback() {
				mainView.showLibrary(null);
			}

			@Override
			public void searchCallback(String searchterm) {
				mainView.showLibrary(searchterm);
			}
		});
		topView.setLocation(0, 0);
		contentPane.add(topView);
		
		mainView = new GUIMainContent();
		mainView.setLocation(0, 50);
		contentPane.add(mainView);
		
		setVisible(true);
		
	}

}
