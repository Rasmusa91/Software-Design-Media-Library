package UserPackage;

import DefaultPackage.*;

public class UserHandlerStateCustomer extends UserHandlerState 
{
	public UserHandlerStateCustomer(IUserHandlerStateChangeCallback stateChangeCallback) 
	{
		super(stateChangeCallback);
	}
	
	@Override
	protected void initialize()
	{
		
	}
	
	@Override
	public boolean logout() 
	{
		user = null;
		
		changeState(new UserHandlerStateNone(stateChangeCallback));
		
		return true;		
	}	
	
	@Override
	public boolean editBalance(float amount) 
	{
		Customer customer = ((Customer) user);
		customer.editBalance(amount);
		
		DatabaseHandler.executeQuery("UPDATE Users "
				+ "SET accountBalance = '" + customer.getBalance() + "' "
				+ "WHERE id = '" + customer.getId() + "'");
		
		return true;
	}	
}
