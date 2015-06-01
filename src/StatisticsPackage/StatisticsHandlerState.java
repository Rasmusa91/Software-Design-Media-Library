package StatisticsPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DefaultPackage.IObserver;
import DefaultPackage.Observable;
import UserPackage.*;

public abstract class StatisticsHandlerState extends Observable implements IObserver 
{
	protected ArrayList<Transaction> transactions;
	private IStatisticsHandlerStateChangeCallback stateChangeCallback;
	protected User user;
	
	public StatisticsHandlerState(IStatisticsHandlerStateChangeCallback stateChangeCallback)
	{
		this.stateChangeCallback = stateChangeCallback;
		transactions = new ArrayList<Transaction>();
	}
	
	protected void initialize()
	{
		
	}
	
	protected void initializeTransactions(ArrayList<HashMap<String, Object>> transactionData)
	{
		for (HashMap<String, Object> hashMap : transactionData) 
		{
			String id = hashMap.get("id").toString();
			String customerId = hashMap.get("userId").toString();
			float value = (float) ((double) hashMap.get("amount"));
			TransactionType type = (value > 0 ? TransactionType.Deposition : TransactionType.Withdrawal);
						
			Date date = null;
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				date = dateFormat.parse(hashMap.get("transactionDate").toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			
			transactions.add(new Transaction(id, customerId, type, value, date));
		}
		notifyObservers(transactions);
	}
	
	public boolean addTransaction(TransactionType type, float value)
	{
		return false;
	}
	
	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
		observer.Update(transactions);
	}
	
	@Override
	public void Update(Object object) 
	{
		user = (User) object;
		
		StatisticsHandlerState handler = null;
		if(user == null) {
			handler = new StatisticsHandlerStateNone(stateChangeCallback);
		}
		else if(user.isStaff()) {
			handler = new StatisticsHandlerStateStaff(stateChangeCallback);			
		}
		else {
			handler = new StatisticsHandlerStateCustomer(stateChangeCallback);
		}

		handler.setUser(user);
		handler.initialize();
		stateChangeCallback.stateChange(handler);
		
	}
	
	private void setUser(User user)
	{
		this.user = user;
	}		
}
