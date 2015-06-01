package GUIPackage;
import java.util.HashMap;

import javax.swing.JPanel;

import UserPackage.User;
import DefaultPackage.IObserver;
import DefaultPackage.Observable;


public class GUIAccountView extends JPanel implements IObserver {

	private GUIAccountCustomer customerView;
	private GUIAccountStaff staffView;
	private JPanel contentView;
	private User user;
	
	public GUIAccountView(HashMap<String, Observable> observableList) {
		setBounds(0,0,860,550);
		setLayout(null);
		observableList.get("userHandler").addObserver(this);
		
		staffView = new GUIAccountStaff(observableList);
		customerView = new GUIAccountCustomer();
	}
	
	private void changeView(JPanel newView)
	{
		if(contentView != null) {
			remove(contentView);
		}
		contentView = newView;
		add(newView);	
		revalidate();
		repaint();
	}

	@Override
	public void Update(Object object) {
		user = (User) object;
		if(user != null) {
			if(user.isStaff()) {
				changeView(staffView);
			} else {
				changeView(customerView);
			}
		}
	}

}
