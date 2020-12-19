package com.polytech.atles.demineur;

import com.polytech.atles.observer.Observable;

public class Box extends Observable
{
	private boolean visible;
	private boolean flag;
	private boolean bomb;
	private int neightborBomb;
	private boolean loose;
	
	public Box()
	{
		init();
	}
	
	public void init()
	{
		this.bomb = false;
		visible = false;
		flag = false;
		neightborBomb = 0;
		loose = false;
		
		this.notifyObservers(null);
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
		this.notifyObservers(null);
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
		this.notifyObservers(null);
	}
	
	public void switchFlag()
	{
		this.flag = !flag;
		this.notifyObservers(null);
	}

	public boolean isBomb()
	{
		return bomb;
	}

	public void setBomb(boolean bomb)
	{
		this.bomb = bomb;
	}

	public int getNeightborBomb()
	{
		return neightborBomb;
	}

	public void setNeightborBomb(int neightborBomb)
	{
		this.neightborBomb = neightborBomb;
	}
	
	public void setLoose(boolean l)
	{
		this.loose = l;
	}
	
	public boolean isLoose()
	{
		return loose;
	}
}
