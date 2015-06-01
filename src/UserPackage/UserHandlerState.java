package UserPackage;

import java.util.ArrayList;

import DefaultPackage.*;
import MediaPackage.*;

public abstract class UserHandlerState extends Observable
{
	protected User user;
	protected IUserHandlerStateChangeCallback stateChangeCallback;
	
	public UserHandlerState(IUserHandlerStateChangeCallback stateChangeCallback)
	{
		this.stateChangeCallback = stateChangeCallback;
	}
	
	public boolean login(String name, String password) {
		return false;		
	}
	
	public boolean logout() {
		return false;		
	}
	
	public boolean createUser(String name, String password) {
		return false;		
	}
	
	public boolean editBalance(float amount) {
		return false;		
	}
	
	protected void setUser(User user)
	{
		this.user = user;
	}
	
	protected void changeState(UserHandlerState userHandlerState)
	{
		userHandlerState.setUser(user);
		stateChangeCallback.stateChange(userHandlerState);	
		notifyObservers(user);
		userHandlerState.setObserverList(observers);
	}
	
	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
		observer.Update(user);
	}
}
