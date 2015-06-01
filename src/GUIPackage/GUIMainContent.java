package GUIPackage;
import java.util.HashMap;

import javax.swing.JPanel;

import MediaPackage.Media;
import MediaPackage.MediaStatus;
import MediaPackage.MediaType;
import TicketPackage.Ticket;
import DefaultPackage.Observable;


@SuppressWarnings("serial")
public class GUIMainContent extends JPanel {
	public interface IMainCallback {
		public boolean onLogin(String name, String pw);
		public boolean onRegister(String name, String pw);
		public boolean onProcessTicket(Ticket ticket, String answer);
		public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type);
		public boolean onAddBalance(float amount, String[] credentials);
		public boolean onRent(Media media);
	}
	private GUILibraryView libraryView;
	private GUIAccountView accountView;
	private JPanel contentView;
	private GUILogin.ILoginCallback loginCallback;
	private GUILibraryView.IOnRentCallback libraryCallback;
	private IMainCallback mainCallback;
	private HashMap<String, Observable> observable;
	
	public GUIMainContent(IMainCallback callback, HashMap<String, Observable> observableList) {
		setBounds(0, 0, 860, 550);
		setLayout(null);
		mainCallback = callback;
		observable = observableList;
		
		libraryCallback = new GUILibraryView.IOnRentCallback() {

			@Override
			public boolean onRent(Media media) {
				return mainCallback.onRent(media);
			}
		};
		libraryView = new GUILibraryView(libraryCallback, observableList);
		libraryView.setLocation(0, 0);
		
		accountView = new GUIAccountView(observableList, new GUIAccountView.IAccountView() {
			
			@Override
			public void onProcessTicket(Ticket ticket, String answer) {
				mainCallback.onProcessTicket(ticket, answer);
			}

			@Override
			public boolean onAddMedia(String name, float price, int amount, MediaStatus status, MediaType type) {
				return mainCallback.onAddMedia(name, price, amount, status, type);
			}

			@Override
			public boolean onAddBalance(float amount, String[] credentials) {
				return mainCallback.onAddBalance(amount, credentials);
			}
		});
		accountView.setLocation(0, 0);
		
		loginCallback = new GUILogin.ILoginCallback() {
			
			@Override
			public boolean login(String name, String pw) {
				boolean result = mainCallback.onLogin(name, pw);
				if(result) {
					changeView(accountView);
				}
				return result;
			}
		};
		
		contentView = libraryView;
		add(contentView);
	}
	
	public void showLogin() {
		changeView(new GUILogin(loginCallback));
	}
	
	public void showRegister() {
		changeView(new GUIRegister(new GUIRegister.IRegisterCallback() {
			
			@Override
			public boolean register(String name, String pw) {
				boolean result = mainCallback.onRegister(name, pw);
				if(result) {
					loginCallback.login(name, pw);
				}
				return result;
			}
		}));
	}
	
	public void showAccount() {
		changeView(accountView);
	}
	
	public void showLibrary(String searchterm) {
		observable.get("mediaHandler").removeObserver(libraryView);
		libraryView = new GUILibraryView(libraryCallback, observable);
		libraryView.search(searchterm);
		changeView(libraryView);	
	}
	
	private void changeView(JPanel newView) {
		remove(contentView);
		contentView = newView;
		add(newView);
		revalidate();
		repaint();
	}
}
