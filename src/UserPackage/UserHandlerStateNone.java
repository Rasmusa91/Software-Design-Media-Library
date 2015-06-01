package UserPackage;

import java.util.ArrayList;
import java.util.HashMap;
import DefaultPackage.*;

public class UserHandlerStateNone extends UserHandlerState
{

	public UserHandlerStateNone(
			IUserHandlerStateChangeCallback stateChangeCallback) {
		super(stateChangeCallback);
	}

	@Override
	public boolean login(String name, String password) 
	{
		boolean success = false;
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Users "
				+ "WHERE name = '" + name + "' AND pw = '" + password + "'");
		//FIXME snygga till mig
		if(result.isEmpty()) {
			return false;
		}
		HashMap<String, Object> data = result.get(0);

		if(((String) data.get("isStaff")).equals("true")) 
		{			
			user = new Staff(data.get("id").toString(), (String) data.get("name"));
			success = true;
			
			changeState(new UserHandlerStateStaff(stateChangeCallback));
		}
		else 
		{
			user = new Customer(data.get("id").toString(), (String) data.get("name"), (float) ((double) data.get("accountBalance")));
			success = true;
			
			changeState(new UserHandlerStateCustomer(stateChangeCallback));
		}
		
		return success;
	}

	@Override
	public boolean createUser(String name, String password) 
	{
		boolean success = false;
		
		ArrayList<HashMap<String, Object>> result = DatabaseHandler.executeQueryAndFetch("SELECT * FROM Users "
				+ "WHERE name = '" + name + "' AND pw = '" + password + "'");
		
		if(result.size() == 0)
		{
			DatabaseHandler.executeQuery("INSERT INTO Users "
					+ "(name, pw, isStaff, accountBalance) "
					+ "VALUES ('" + name +  "', '" + password + "', 'false', 0)");
					
			
			success = true;
		}
		
		return success;
	}
}