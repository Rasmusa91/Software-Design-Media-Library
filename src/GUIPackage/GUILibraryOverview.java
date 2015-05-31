package GUIPackage;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class GUILibraryOverview extends JPanel {
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getClickCount() == 2) {
				System.out.println(mediaList.getSelectedValue());
				selectCallback.onSelectCallback(mediaList.getSelectedValue());
			}
		}
	}
	
	public interface IOnSelectCallback {
		public void onSelectCallback(String id);
	}
	
	private JLabel topLabel;
	private JScrollPane scroll;
	private JList<String> mediaList;
	private Vector<String> mediaData;
	
	private IOnSelectCallback selectCallback;
	
	public GUILibraryOverview(IOnSelectCallback callback) {
		setBounds(0, 0, 844, 561);
		setLayout(null);
		selectCallback = callback;
		
		topLabel = new JLabel("Library - Search results for \"medianame\"");
		topLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		topLabel.setBounds(10, 10, 600, 30);
		add(topLabel);
		
		mediaData = new Vector<String>();
		mediaData.add("MediaName");
		
		mediaList = new JList<String>();
		mediaList.setListData(mediaData);
		mediaList.addMouseListener(new LabelMouseAdapter());
		
		scroll = new JScrollPane(mediaList);
		scroll.setBounds(10, 50, 824, 446);
		add(scroll);
	}
}
