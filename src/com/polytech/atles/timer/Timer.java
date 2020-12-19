package com.polytech.atles.timer;

import com.polytech.atles.executor.ExecutorManager;
import com.polytech.atles.observer.Observable;

public class Timer extends Observable
{
	private Horloge horloge;
	private boolean running;
	private long lastTimeMillis;
	
	private final long interval = 1000;
	
	public Timer()
	{
		initialize();
		running = false;
		lastTimeMillis = 0;
	}
	
	public void initialize()
	{
		this.horloge = new Horloge();
		this.notifyObservers(null);
	}
	
	public void start()
	{
		if(running)
			return;
		
		running = true;
		Runnable r = new Runnable()
		{
			public void run() {
                while(running)
                {
                	update();
                	try
					{
						Thread.sleep(100);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
		};
		
		//Thread t = new Thread(r);
		//t.setDaemon(true);
		
		lastTimeMillis = System.currentTimeMillis();
		ExecutorManager.getInstance().execute(r);
		//t.start();
	}
	
	public void stop()
	{
		this.running = false;
		this.notifyObservers(null);
	}
	
	public void update()
	{
		long current = System.currentTimeMillis();
		long dif = current - lastTimeMillis;
		if(dif > interval)
		{
			this.horloge.nextSeconde();
			lastTimeMillis = current - (dif - interval);
			this.notifyObservers(null);
		}
	}
	
	public String getTime()
	{
		return horloge.toString();
	}

	public void removeAllObservers()
	{
		// TODO Auto-generated method stub
		
	}
}
