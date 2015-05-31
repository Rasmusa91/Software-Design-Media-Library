package GUIPackage;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUIMainContent extends JPanel {
	
	private GUILibraryView libraryView;
	private GUIAccountView accountView;
	private JPanel contentView;
	private GUILogin.ILoginCallback loginCallback;
	private GUILibraryView.IOnSelectCallback libraryCallback;
	
	public GUIMainContent() {
		setBounds(0, 0, 860, 550);
		setLayout(null);
		libraryCallback = new GUILibraryView.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback() {
				System.out.println("guilibraryview");
				
			}
		};
		libraryView = new GUILibraryView(libraryCallback, null);
		libraryView.setLocation(0, 0);
		
		accountView = new GUIAccountView();
		accountView.setLocation(0, 0);
		
		loginCallback = new GUILogin.ILoginCallback() {
			
			@Override
			public boolean login(String name, String pw) {
				if(name.equals("jesper") && pw.equals("j")) {
					changeView(accountView);
					return true;
				}
				return false;
				
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
				if(!name.equals("jesper")) {
					return true;
				}
				return false;
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
