package com.polytech.atles.demineur.observer;

import com.polytech.atles.observer.Observable;
import com.polytech.atles.observer.Observer;

import javafx.application.Platform;

public abstract class BoxObserver implements Observer
{

	@Override
	public final void update(Observable observable, Object o)
	{
		Platform.runLater(new Runnable()
		{
			@Override
			public void run()
			{
				updateView(observable, o);
				
			}
		});
	}
	
	protected abstract void updateView(Observable observable, Object o);

}
