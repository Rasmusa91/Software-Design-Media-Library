package GUIPackage;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUILibraryView extends JPanel {
	
	public interface IOnSelectCallback {
		public void onSelectCallback();
	}
	
	private IOnSelectCallback selectCallback;
	private JPanel contentView;
	private GUILibraryOverview listView;
	
	public GUILibraryView(IOnSelectCallback callback, String searchterm) {
		if(searchterm == null) {
			
		}
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		selectCallback = callback;
		
		listView = new GUILibraryOverview(new GUILibraryOverview.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback(String id) {
				changeView(new GUILibrarySingle(new GUILibrarySingle.IOnRentCallback() {
					
					@Override
					public boolean onRent() {
						//TODO
						return false;
					}
				}));
			}
		});
		
		contentView = listView;
		add(contentView);
	}
	
	private void changeView(JPanel newView)
	{
		
		remove(contentView);
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}
}
