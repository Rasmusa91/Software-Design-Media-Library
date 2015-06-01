package GUIPackage;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;

import DefaultPackage.IObserver;
import DefaultPackage.Observable;
import StatisticsPackage.Transaction;
import StatisticsPackage.TransactionType;


@SuppressWarnings("serial")
public class GUIAccountStaffFinancialStatisticsView extends JPanel implements IObserver {
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
			int monthNr = 1;
			float monthSum = 0;
			for(int i = 0; i < 12; i++) {
				String month = "<html><u>" + months[i] + "</u></html>";
				if(((JLabel)arg0.getSource()).getText().equals(month)) {
					monthNr += i;
					break;
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
			for (Transaction transaction : transactions) {
				if(Integer.valueOf(dateFormat.format(transaction.getDate())) == monthNr) {
					if(transaction.getType() == TransactionType.Deposition) {
						monthSum += transaction.getValue();
					}
				}
			}
			sum.setText("Total Sum: " + String.valueOf(monthSum) + ":-");
			
		}
	}
	
	private JLabel financial;
	private JLabel year;
	private JLabel sum;
	private JPanel monthspanel;
	private ArrayList<Transaction> transactions;
	
	public GUIAccountStaffFinancialStatisticsView(HashMap<String, Observable> observableList) {
		setBounds(0,0,654,471);
		setLayout(null);
		
		observableList.get("statisticsHandler").addObserver(this);
		//statisticsHandler.addObserver(this);
		
		financial = new JLabel("Financial Statistics");
		financial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		financial.setBounds(10, 10, 150, 25);
		add(financial);
		
		year = new JLabel("2015");
		year.setBounds(10, 45, 46, 14);
		add(year);
		
		sum = new JLabel("Press a month to see the total sum of that month.");
		sum.setBounds(10, 105, 500, 14);
		add(sum);
		
		monthspanel = new JPanel();
		monthspanel.setBounds(10, 70, 634, 25);
		monthspanel.setLayout(null);
		add(monthspanel);
		
		LabelMouseAdapter labelAdapter = new LabelMouseAdapter();
		
		int width = 30;
		int x_pos = 40;
		String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
		
		for(int i = 0; i < 12; i++) {
			JLabel month = new JLabel("<html><u>" + months[i] + "</u></html>");
			month.setBounds(x_pos * i, 5, width, 14);
			month.setCursor(new Cursor(Cursor.HAND_CURSOR));
			month.addMouseListener(labelAdapter);
			monthspanel.add(month);
			if(i != 11) {
				JLabel divider = new JLabel("|");
				divider.setBounds((x_pos * i) + width, 5, 5, 14);
				monthspanel.add(divider);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void Update(Object object) {
		transactions = (ArrayList<Transaction>) object;
	}

}
