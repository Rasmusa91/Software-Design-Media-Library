package GUIPackage;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import MediaPackage.Media;
import MediaPackage.MediaStatus;
import MediaPackage.MediaType;
import TicketPackage.Ticket;
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
				guiCallback.onLogout();
				mainView.showLibrary(null);
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

			@Override
			public void accountCallback() {
				mainView.showAccount();
				
			}
		}, observableList.get("userHandler"));
		topView.setLocation(0, 0);
		contentPane.add(topView);
		
		mainView = new GUIMainContent(new GUIMainContent.IMainCallback() {
			@Override
			public boolean onLogin(String name, String pw) {
				return guiCallback.onLogin(name, pw);
			}

			@Override
			public boolean onRegister(String name, String pw) {
				return guiCallback.onSignup(name, pw);
			}

			@Override
			public boolean onProcessTicket(Ticket ticket, String answer) {
				return guiCallback.onProcessTicket(ticket, answer);
			}

			@Override
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type) {
				return guiCallback.onAddMedia(name, price, amount, status, type);
			}

			@Override
			public boolean onAddBalance(float amount, String[] credentials) {
				return guiCallback.onAddFunds(amount, credentials);
			}

			@Override
			public boolean onRent(Media media) {
				return guiCallback.onRentMedia(media);
			}
			
		}, observableList);
		mainView.setLocation(0, 50);
		contentPane.add(mainView);
		
		setVisible(true);
		
	}

}
