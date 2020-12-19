package com.polytech.atles.hex;

import com.polytech.atles.demineur.Position;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

public class HexBoard extends Canvas implements EventHandler<MouseEvent>
{
	private HexGraphic hexGraphic;
	private HexMetrics metrics;
	private double[] cornersX;
	private double[] cornersY;
	
	private double[] center;
	
	private int width;
	private int height;

	private HexCellHandler hexCellHandler;

	public HexBoard(int width, int height, int radius)
	{
		this.width = width;
		this.height = height;

		hexGraphic = new HexGraphic(this.getGraphicsContext2D());
		metrics = new HexMetrics(radius);
		cornersX = new double[6];
		cornersY = new double[6];
		
		center = new double[2];

		this.setWidth(((width * radius * 3) / 2) + (radius / 2));
		double v = 0;
		if ((height > 1))
			v = metrics.getHeight() / 2;
		this.setHeight(metrics.getHeight() * height + v);

		this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
	}

	@Override
	public void handle(MouseEvent event)
	{
		Position index = metrics.indexByPoint(event.getX(), event.getY());
		if (isOnBoard(index.getX(), index.getY())) 
			if (hexCellHandler != null) 
				hexCellHandler.update(index.getX(), index.getY(), event);
	}


	public boolean isOnBoard(int x, int y)
	{
		return (x > -1) && (x < width) && (y > -1) && (y < height);
	}

	public void repaint()
	{
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
				repaintCell(x, y);
		}
	}

	public void repaintCell(int x, int y) 
	{
		metrics.computeCorners(x, y, cornersX, cornersY, center);
		hexGraphic.draw(cornersX, cornersY, center);
	}
	
	public HexCellHandler getHexCellHandler()
	{
		return hexCellHandler;
	}

	public void setHexCellHandler(HexCellHandler handler)
	{
		hexCellHandler = handler;
	}
	
	public HexGraphic getHexGraphic()
	{
		return hexGraphic;
	}
}
