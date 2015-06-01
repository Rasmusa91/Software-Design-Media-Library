package StatisticsPackage;

import java.util.ArrayList;
import java.util.HashMap;

import DefaultPackage.DatabaseHandler;

public class StatisticsHandlerStateStaff extends StatisticsHandlerState {

	public StatisticsHandlerStateStaff(IStatisticsHandlerStateChangeCallback stateChangeCallback) 
	{
		super(stateChangeCallback);
	}
	
	@Override
	protected void initialize()
	{
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Transactions");
		
		initializeTransactions(result);
		
	}	
}
