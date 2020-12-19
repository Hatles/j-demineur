package com.polytech.atles.hex;

import com.polytech.atles.demineur.Position;

public class HexMetrics
{
	private double width;
	private double height;
	private double side;
	private double radius;

	private double[] cornersX;
	private double[] cornersY;
	
	private double centerX;
	private double centerY;

	public HexMetrics(double radius)
	{
		this.radius = radius;
		width = radius * 2;
		height = radius * Math.sqrt(3);
		side = radius * 3 / 2;

		cornersX = new double[] { radius / 2, side, width, side, radius / 2, 0 };
		cornersY = new double[] { 0, 0, height / 2, height, height, height / 2 };
		
		centerX = width/2;
		centerY = height/2;
	}

	public void computeCorners(int x, int y, double[] cX, double[] cY, double[] center)
	{
		double mX = x * side;
		double mY = height * (2 * y + (x % 2)) / 2;
		for (int i = 0; i < 6; i++)
		{
			cX[i] = mX + cornersX[i];
			cY[i] = mY + cornersY[i];
		}
		
		center[0] = mX + this.centerX;
		center[1] = mY + this.centerY;
	}

	public Position indexByPoint(double x, double y)
	{
		double ci = Math.floor(x / side);
		double cx = x - side * ci;

		double ty = y - (ci % 2) * height / 2;
		double cj = Math.floor(ty / height);
		double cy = ty - height * cj;

		if (cx > Math.abs(radius / 2 - radius * cy / height))
			return new Position((int) ci, (int) cj);
		else
		{
			double px = ci - 1;
			double py = cj + (ci % 2);
			if ((cy < height / 2))
				py -= 1;
			return new Position((int) px, (int) py);
		}
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getSide()
	{
		return side;
	}

	public void setSide(double side)
	{
		this.side = side;
	}

	public double getRadius()
	{
		return radius;
	}

	public void setRadius(double radius)
	{
		this.radius = radius;
	}

	public double[] getCornersX()
	{
		return cornersX;
	}

	public void setCornersX(double[] cornersX)
	{
		this.cornersX = cornersX;
	}

	public double[] getCornersY()
	{
		return cornersY;
	}

	public void setCornersY(double[] cornersY)
	{
		this.cornersY = cornersY;
	}
}
