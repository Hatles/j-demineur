package com.polytech.atles.demineur;

public class Position3D
{
	private int x;
	private int y;
	private int z;
	
	public Position3D(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Position3D()
	{
		this(0,0,0);
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
	
	public int getZ()
	{
		return z;
	}

	public void setZ(int z)
	{
		this.z = z;
	}
	
	public Position3D moveRight()
	{
		x++;
		return this;
	}
	
	public Position3D moveLeft()
	{
		x--;
		return this;
	}
	
	public Position3D moveTop()
	{
		y--;
		return this;
	}
	
	public Position3D moveBottom()
	{
		y++;
		return this;
	}
	
	public Position3D moveBehind()
	{
		z++;
		return this;
	}
	
	public Position3D moveForward()
	{
		z--;
		return this;
	}
}
