package GUIPackage;
import javax.swing.JPanel;


public class GUIAccountView extends JPanel {

	private GUIAccountCustomer customerView;
	private GUIAccountStaff staffView;
	private JPanel contentView;
	public GUIAccountView() {
		setBounds(0,0,860,550);
		setLayout(null);
		
		contentView = new GUIAccountCustomer();
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
