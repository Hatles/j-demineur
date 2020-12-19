package com.polytech.atles.demineur;

public class GridOption
{
	private int[] dimensions;
	private int nbBombs;
	
	public GridOption(int size)
	{
		dimensions = new int[size];
		for(int i = 0; i < size; i++)
			dimensions[i] = 1;
		nbBombs = 1;
	}
	
	public GridOption(int[] d, int b)
	{
		dimensions = d;
		nbBombs = b;
	}

	public int[] getDimensions()
	{
		return dimensions;
	}

	public void setDimensions(int[] dimensions)
	{
		this.dimensions = dimensions;
	}

	public int getNbBombs()
	{
		return nbBombs;
	}

	public void setNbBombs(int nbBombs)
	{
		this.nbBombs = nbBombs;
	}
}
