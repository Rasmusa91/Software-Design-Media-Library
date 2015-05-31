package StatisticsPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DefaultPackage.DatabaseHandler;

public class StatisticsHandlerStateCustomer extends StatisticsHandlerState {

	public StatisticsHandlerStateCustomer(IStatisticsHandlerStateChangeCallback stateChangeCallback) 
	{
		super(stateChangeCallback);
	}

	@Override
	protected void initialize()
	{
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Transactions "
				+ "WHERE userId = '" + user.getId() + "'");
		
		initializeTransactions(result);
	}
	
	@Override
	public boolean addTransaction(TransactionType type, float value)
	{		
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateString = dateFormat.format(date);

		float amount = value * (type == TransactionType.Deposition ? 1 : -1);
		
		DatabaseHandler.executeQuery("INSERT INTO Transactions "
				+ "(userId, amount, transactionDate) "
				+ "VALUES('" + user.getId() + "', '" + amount + "', '" + dateString  + "')");
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT MAX(id) AS LastID FROM Transactions");
		String lastID = result.get(0).get("LastID").toString();
		
		transactions.add(new Transaction(lastID, user.getId(), type, value, date));
		
		return true;
	}	
}
