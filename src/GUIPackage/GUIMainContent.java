package GUIPackage;
import java.util.HashMap;

import javax.swing.JPanel;

import DefaultPackage.Observable;


@SuppressWarnings("serial")
public class GUIMainContent extends JPanel {
	public interface IMainCallback {
		public boolean onLogin(String name, String pw);
		public boolean onRegister(String name, String pw);
	}
	private GUILibraryView libraryView;
	private GUIAccountView accountView;
	private JPanel contentView;
	private GUILogin.ILoginCallback loginCallback;
	private GUILibraryView.IOnSelectCallback libraryCallback;
	private IMainCallback mainCallback;
	
	public GUIMainContent(IMainCallback callback, HashMap<String, Observable> observableList) {
		setBounds(0, 0, 860, 550);
		setLayout(null);
		mainCallback = callback;
		libraryCallback = new GUILibraryView.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback() {
				System.out.println("guilibraryview");
				
			}
		};
		libraryView = new GUILibraryView(libraryCallback, null);
		libraryView.setLocation(0, 0);
		
		accountView = new GUIAccountView(observableList);
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
		
		libraryView = new GUILibraryView(libraryCallback, searchterm);
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
