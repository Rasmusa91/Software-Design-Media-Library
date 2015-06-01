package GUIPackage;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import DefaultPackage.IObserver;
import DefaultPackage.Observable;
import MediaPackage.Media;


@SuppressWarnings("serial")
public class GUILibraryView extends JPanel implements IObserver {
	
	public interface IOnRentCallback {
		public boolean onRent(Media media);
	}
	
	private IOnRentCallback rentCallback;
	private JPanel contentView;
	private GUILibraryOverview listView;
	private ArrayList<Media> media;
	private ArrayList<String> indexes;
	private String searchterm;
	
	public GUILibraryView(IOnRentCallback callback, HashMap<String, Observable> observableList) {

		setBounds(0,0,654,471);
		setLayout(null);
		
		rentCallback = callback;
		
		listView = new GUILibraryOverview(new GUILibraryOverview.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback(int id) {
				for (Media m : media) {
					if(m.getId().equals(indexes.get(id))) {
						changeView(new GUILibrarySingle(new GUILibrarySingle.IOnRentCallback() {
							
							@Override
							public boolean onRent(Media media) {
								return rentCallback.onRent(media);
							}
						}, m));
					}
				}
			}
		});
		
		contentView = listView;
		add(contentView);
		
		observableList.get("mediaHandler").addObserver(this);
	}
	
	private void changeView(JPanel newView)
	{
		remove(contentView);
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}
	
	public void search(String search) {
		indexes = new ArrayList<String>();
		searchterm = search;
		if(search != null) {
			ArrayList<Media> searchData = new ArrayList<Media>();
			for (Media m : media) {
				if(m.getName().toLowerCase().contains(searchterm.toLowerCase())){
					searchData.add(m);
					indexes.add(m.getId());
				}
			}
			listView.update(searchData, search);
		} else {
			listView.update(media, search);
			for(Media m : media) {
				indexes.add(m.getId());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Update(Object object) {
		media = (ArrayList<Media>) object;
		search(searchterm);
	}
}
