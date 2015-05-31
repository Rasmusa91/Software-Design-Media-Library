package GUIPackage;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class GUIAccountCustomerTransactionHistoryView extends JPanel {

	private JLabel transaction;
	
	private JScrollPane scroll;
	private JList<String> transactionList;
	private Vector<String> transactionData;
	
	public GUIAccountCustomerTransactionHistoryView() {

		setBounds(0,0,654,471);
		setLayout(null);
		
		transaction = new JLabel("Transactions");
		transaction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		transaction.setBounds(10, 10, 150, 25);
		add(transaction);
		
		transactionData = new Vector<String>();
		transactionData.add("TransactionName");
		
		transactionList = new JList<String>();
		transactionList.setListData(transactionData);
		
		scroll = new JScrollPane(transactionList);
		scroll.setBounds(10, 50, 635, 410);
  
 		add(scroll);
 		
	}

}
