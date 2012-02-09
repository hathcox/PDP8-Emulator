package edu.uat.csc263.su11045.ixk.observerpattern;


public interface Observable 
{
	public void updateObservers();
	public void addObserver(Observer observer);
}
