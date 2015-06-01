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
	
	public abstract void addObserver(IObserver observer);
	
	public void notifyObservers(Object object)
	{
		for (IObserver observer : observers) {
			observer.Update(object);
			System.out.println(observer.getClass().toString());
		}
	}
	
	public void updateReference(IObserver newObserver, IObserver oldObserver)
	{
		removeObserver(oldObserver);
		observers.add(newObserver);
	}
	
	protected void setObserverList(ArrayList<IObserver> list)
	{
		observers = list;
	}
}
