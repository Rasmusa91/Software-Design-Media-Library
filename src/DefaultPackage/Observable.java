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
		for(int i = 0; i < observers.size(); i++){
			observers.get(i).Update(object);
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
