package GUIPackage;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


@SuppressWarnings("serial")
public class GUIAccountCustomerAddBalanceView extends JPanel {

	
	public interface IAddBalanceCallback{
		public boolean onAddAmount(float amount, String[] credentials);
		
	}
	
	private IAddBalanceCallback balanceCallback;
	
	private float amountSave;
	private JPanel contentView;
	
	private GUIAccountCustomerAddAmount addAmountView;
	private GUIAccountCustomerAddCredentials addCredentialsView;

	public GUIAccountCustomerAddBalanceView(IAddBalanceCallback callback) {
		
		balanceCallback = callback;
		
		setBounds(190,40,654,471);
		setLayout(null);
		
		addAmountView = new GUIAccountCustomerAddAmount(new GUIAccountCustomerAddAmount.IAddAmountCallback() {
			
			@Override
			public void onAddAmount(float amount) {
				changeView(addCredentialsView);
				amountSave = amount;
			}
		});
		
		addCredentialsView = new GUIAccountCustomerAddCredentials(new GUIAccountCustomerAddCredentials.IAddCredentialsCallback() {

			@Override
			public boolean onAddCredentials(String[] credentials) {
				return balanceCallback.onAddAmount(amountSave, credentials);
			}
			
		});
		
		contentView = addAmountView;
		
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
