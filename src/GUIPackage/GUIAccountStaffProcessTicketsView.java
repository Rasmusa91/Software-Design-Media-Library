package GUIPackage;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GUIAccountStaffProcessTicketsView extends JPanel {

	public interface IProcessTicketCallback{
		public void onProcessTicket();
	}
	
	private IProcessTicketCallback processCallback;
	
	private JPanel contentView;
	private GUIAccountStaffProcessTicketsOverView listView;

	public GUIAccountStaffProcessTicketsView(IProcessTicketCallback callback) {
		processCallback = callback;
		
		setBounds(0,0,654,471);
		setLayout(null);
		
		listView = new GUIAccountStaffProcessTicketsOverView(new GUIAccountStaffProcessTicketsOverView.IOnSelectCallback() {
			
			@Override
			public void onSelectCallback(String id) {
				changeView(new GUIAccountStaffProcessTicketsSingle(new GUIAccountStaffProcessTicketsSingle.IProcessTicketCallback() {
					
					@Override
					public boolean onProcessTicket(String answer) {
						System.out.println("PROCESSTICKET");
						return true;
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


