package com.polytech.atles.demineur;

import java.util.HashMap;

public class GridSquare extends Grid<GridSquare>
{
	private final int width;
	private final int height;
	
	public GridSquare(Demineur<GridSquare> demineur, int w, int h, int b)
	{
		super(demineur, b);
		this.width = w;
		this.height = h;
	}

	public void initGrid()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				boxes.add(genBox());
			}
		}
	}

	protected HashMap<Box, Box[]> getNeightborMap()
	{
		HashMap<Box, Box[]> neightborMap = new HashMap<Box, Box[]>();
		
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			Box box = this.getBoxes().get(i);
			//Box[] neightbors = get4Neightbors(i);
			Box[] neightbors = getAllNeightbors(i);
			
			neightborMap.put(box, neightbors);
		}
		return neightborMap;
	}
	
	/*private void initBombNeightborhood()
	{
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			Box box = this.getBoxes().get(i);
			Box[] neightbors = getAllNeightbors(i);
			
			int b_count = 0;
			for(int j = 0; j < neightbors.length; j++)
			{
				if(neightbors[j] != null && neightbors[j].isBomb())
					b_count++;
			}
			box.setNeightborBomb(b_count);
		}
	}*/
	
	private Box[] getAllNeightbors(int i)
	{
		int[] indices = getAllNeightborsIndices(i);
		Box[] neightbors = new Box[indices.length];
		
		for(int j = 0; j < indices.length; j++)
		{
			int index = indices[j];
			
			if(index >= 0 && index < this.getBoxes().size())
			{
				neightbors[j] = this.getBoxes().get(index);
			}
		}
		return neightbors;
	}
	
	private int[] getAllNeightborsIndices(int i)
	{
		Position[] positions = getAllNeightborsPositions(this.getPosition(i));
		
		int n = 0;
		for(int j = 0; j < positions.length; j++)
		{
			if(this.getIndex(positions[j]) != -1)
				n++;
		}
		
		int[] n_indices = new int[n];
		n = 0;
		for(int j = 0; j < positions.length; j++)
		{
			if(this.getIndex(positions[j]) != -1)
			{
				n_indices[n] = this.getIndex(positions[j]);
				n++;
			}
		}
		
		return n_indices;
	}
	
	private Position[] getAllNeightborsPositions(Position pos)
	{
		Position[] positions = new Position[8];
		positions[0] = new Position(pos.getX()+1, pos.getY());
		positions[1] = new Position(pos.getX()+1, pos.getY()+1);
		positions[2] = new Position(pos.getX(), pos.getY()+1);
		positions[3] = new Position(pos.getX()-1, pos.getY()+1);
		positions[4] = new Position(pos.getX()-1, pos.getY());
		positions[5] = new Position(pos.getX()-1, pos.getY()-1);
		positions[6] = new Position(pos.getX(), pos.getY()-1);
		positions[7] = new Position(pos.getX()+1, pos.getY()-1);
		return positions;
	}
	
	private Position getPosition(int i)
	{
		if(i >= 0 && i < width*height)
		{
			int y = i/width;
			int x = i - y*width;
			return new Position(x, y);
		}
		return null;
	}
	
	private int getIndex(Position p)
	{
		return getIndex(p.getX(), p.getY());
	}
	
	private int getIndex(int x, int y)
	{
		if(x >= 0 && x < width && y >= 0 && y < height)
		{
			return y*width + x;
		}
		return -1;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
