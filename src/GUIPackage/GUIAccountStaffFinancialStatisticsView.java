package GUIPackage;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GUIAccountStaffFinancialStatisticsView extends JPanel {
	
	private class LabelMouseAdapter extends MouseAdapter {
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println(((JLabel)arg0.getSource()).getText());
		}
	}
	
	private JLabel financial;
	private JLabel year;
	private JLabel sum;
	private JPanel monthspanel;
	
	public GUIAccountStaffFinancialStatisticsView() {
		setBounds(0,0,654,471);
		setLayout(null);
		
		financial = new JLabel("Financial Statistics");
		financial.setFont(new Font("Tahoma", Font.PLAIN, 15));
		financial.setBounds(10, 10, 150, 25);
		add(financial);
		
		year = new JLabel("2015");
		year.setBounds(10, 45, 46, 14);
		add(year);
		
		sum = new JLabel("Total Sum: 2000:-");
		sum.setBounds(10, 105, 100, 14);
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

}
