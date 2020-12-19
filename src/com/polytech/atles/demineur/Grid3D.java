package com.polytech.atles.demineur;

import java.util.HashMap;

public class Grid3D extends Grid<Grid3D>
{
	private final int width;
	private final int height;
	private final int depth;
	
	public Grid3D(Demineur<Grid3D> demineur, int w, int h, int d, int b)
	{
		super(demineur, b);
		this.width = w;
		this.height = h;
		this.depth = d;
	}

	public void initGrid()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				for(int k = 0; k < depth; k++)
				{
					boxes.add(genBox());
				}
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
		Position3D[] positions = getAllNeightborsPositions(this.getPosition(i));
		
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
	
	private Position3D[] getAllNeightborsPositions(Position3D pos)
	{
		Position3D[] positions = new Position3D[26];
		positions[0] = new Position3D(pos.getX()+1, pos.getY(), pos.getZ());
		positions[1] = new Position3D(pos.getX()+1, pos.getY()+1, pos.getZ());
		positions[2] = new Position3D(pos.getX(), pos.getY()+1, pos.getZ());
		positions[3] = new Position3D(pos.getX()-1, pos.getY()+1, pos.getZ());
		positions[4] = new Position3D(pos.getX()-1, pos.getY(), pos.getZ());
		positions[5] = new Position3D(pos.getX()-1, pos.getY()-1, pos.getZ());
		positions[6] = new Position3D(pos.getX(), pos.getY()-1, pos.getZ());
		positions[7] = new Position3D(pos.getX()+1, pos.getY()-1, pos.getZ());
		
		positions[8] = new Position3D(pos.getX(), pos.getY(), pos.getZ()+1);
		positions[9] = new Position3D(pos.getX()+1, pos.getY(), pos.getZ()+1);
		positions[10] = new Position3D(pos.getX()+1, pos.getY()+1, pos.getZ()+1);
		positions[11] = new Position3D(pos.getX(), pos.getY()+1, pos.getZ()+1);
		positions[12] = new Position3D(pos.getX()-1, pos.getY()+1, pos.getZ()+1);
		positions[13] = new Position3D(pos.getX()-1, pos.getY(), pos.getZ()+1);
		positions[14] = new Position3D(pos.getX()-1, pos.getY()-1, pos.getZ()+1);
		positions[15] = new Position3D(pos.getX(), pos.getY()-1, pos.getZ()+1);
		positions[16] = new Position3D(pos.getX()+1, pos.getY()-1, pos.getZ()+1);
		
		positions[17] = new Position3D(pos.getX(), pos.getY(), pos.getZ()-1);
		positions[18] = new Position3D(pos.getX()+1, pos.getY(), pos.getZ()-1);
		positions[19] = new Position3D(pos.getX()+1, pos.getY()+1, pos.getZ()-1);
		positions[20] = new Position3D(pos.getX(), pos.getY()+1, pos.getZ()-1);
		positions[21] = new Position3D(pos.getX()-1, pos.getY()+1, pos.getZ()-1);
		positions[22] = new Position3D(pos.getX()-1, pos.getY(), pos.getZ()-1);
		positions[23] = new Position3D(pos.getX()-1, pos.getY()-1, pos.getZ()-1);
		positions[24] = new Position3D(pos.getX(), pos.getY()-1, pos.getZ()-1);
		positions[25] = new Position3D(pos.getX()+1, pos.getY()-1, pos.getZ()-1);
		return positions;
	}
	
	private Position3D getPosition(int i)
	{
		if(i >= 0 && i < width*height*depth)
		{	
			int z = i/(height*width);
			int idx = i - z*(height*width);
			int y = idx/width;
			int x = idx - y*width;
			return new Position3D(x, y, z);
		}
		return null;
	}
	
	private int getIndex(Position3D p)
	{
		return getIndex(p.getX(), p.getY(), p.getZ());
	}
	
	private int getIndex(int x, int y, int z)
	{
		if(x >= 0 && x < width && y >= 0 && y < height && z >= 0 && z < depth)
		{
			return z*width*height + y*width + x;
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
	
	public int getDepth()
	{
		return depth;
	}
}
