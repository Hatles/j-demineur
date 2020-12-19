package com.polytech.atles.demineur;

public class Position
{
	private int x;
	private int y;
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Position()
	{
		this(0,0);
	}
	

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	
	public Position moveRight()
	{
		x++;
		return this;
	}
	
	public Position moveLeft()
	{
		x--;
		return this;
	}
	
	public Position moveTop()
	{
		y--;
		return this;
	}
	
	public Position moveBottom()
	{
		y++;
		return this;
	}
}
