package UserPackage;

public class UserHandlerStateStaff extends UserHandlerState
{
	public UserHandlerStateStaff(
			IUserHandlerStateChangeCallback stateChangeCallback) {
		super(stateChangeCallback);
	}
	
	@Override
	public boolean logout() 
	{
		user = null;
		
		changeState(new UserHandlerStateNone(stateChangeCallback));
		
		return true;		
	}
}
