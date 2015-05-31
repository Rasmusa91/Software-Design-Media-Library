package DefaultPackage;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable 
{
	protected ArrayList<IObserver> observers;
	
	public Observable()
	{
		observers = new ArrayList<IObserver>();
	}
	
	public void removeObserver(IObserver observer)
	{
		observers.remove(observer);
	}
	
	public void addObserver(IObserver observer)
	{
		observers.add(observer);
	}
	
	public void notifyObservers(Object object)
	{
		for (IObserver observer : observers) {
			observer.Update(object);
		}
	}
	
	public void updateReference(IObserver newObserver, IObserver oldObserver)
	{
		removeObserver(oldObserver);
		addObserver(newObserver);
	}
	
	protected void setObserverList(ArrayList<IObserver> list)
	{
		observers = list;
	}
}
