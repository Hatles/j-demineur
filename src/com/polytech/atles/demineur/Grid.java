package com.polytech.atles.demineur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.polytech.atles.observer.Observable;

public abstract class Grid<G extends Grid<G>> extends Observable
{
	private Demineur<G> demineur;
	//private float ratio;
	
	protected int bombCount;
	protected List<Box> boxes;
	protected Map<Box, Box[]> neightborMap;
	protected Random rand;
	protected int nbBomb;
	
	public Grid(Demineur<G> demineur, int nbBomb)
	{
		this.demineur = demineur;
		rand = new Random();
		//ratio = 0.1f;
		
		bombCount = 0;
		boxes = new ArrayList<Box>();
		neightborMap = new HashMap<Box, Box[]>();
		this.nbBomb = nbBomb;
	}
	
	public void addBomb()
	{
		bombCount++;
		boolean bomb = false;
		
		do
		{
			int i = rand.nextInt(boxes.size());
			if(!this.boxes.get(i).isBomb());
			{
				bomb = true;
				this.boxes.get(i).setBomb(true);
			}
		}
		while(!bomb);
	}
	
	public void init()
	{
		initGrid();
		genBombs();
		neightborMap = getNeightborMap();
		
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			Box box = this.getBoxes().get(i);
			box.setNeightborBomb(getBombNeightborhood(box));
		}
	}
	
	private void genBombs()
	{
		for(int i = 0; i < nbBomb; i++)
		{
			this.addBomb();
		}
	}

	protected abstract void initGrid();
	protected abstract HashMap<Box, Box[]> getNeightborMap();
	
	protected int getBombNeightborhood(Box box)
	{
		Box[] neightbors = neightborMap.get(box);
		int b_count = 0;
		for(int j = 0; j < neightbors.length; j++)
		{
			if(neightbors[j] != null && neightbors[j].isBomb())
				b_count++;
		}
		return b_count;
	}
	
	public Box[] getNeightbors(Box box)
	{
		return neightborMap.get(box);
	}
	
	public void click(Box box)
	{
		if(!box.isFlag())
		{
			if(box.isBomb())
			{
				box.setLoose(true);
				this.loose();
			}
			else
			{
				List<Box> checked = new ArrayList<Box>();
				check(checked, box);
			}
		}
	}
	
	private void check(List<Box> checked, Box box)
	{
		if(!checked.contains(box))
		{
			checked.add(box);
			
			if(!box.isBomb())
			{
				box.setVisible(true);
				if(box.isFlag())
					bombCount++;
				
				if(box.getNeightborBomb() == 0)
				{
					Box[] n = this.getNeightbors(box);
					for(int i = 0; i < n.length; i++)
						//n[i].setVisible(true);
						check(checked, n[i]);
				}
			}
		}
	
		/*box.setVisible(true);
		Box[] n = this.getNeightbors(box);
		for(int i = 0; i < n.length; i++)
				n[i].setVisible(true);*/
	}
	
	private void loose()
	{
		this.revealAll();
		demineur.loose();
	}

	private void revealAll()
	{
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			this.getBoxes().get(i).setVisible(true);
		}
	}

	public void leftClick(Box box)
	{
		if(!box.isVisible())
		{
			box.switchFlag();
			
			if(box.isFlag())
				this.bombCount--;
			else
				this.bombCount++;
		}
	}
	
	public List<Box> getBoxes()
	{
		return boxes;
	}
	
	protected Box genBox()
	{
		return new Box();
	}
	
	public void reset()
	{
		bombCount = 0;
		
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			this.getBoxes().get(i).init();
		}
		
		genBombs();
		neightborMap = getNeightborMap();
		
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			Box box = this.getBoxes().get(i);
			box.setNeightborBomb(getBombNeightborhood(box));
		}
	}
	
	public int getBombCount()
	{
		return this.bombCount;
	}
	
	public void isWin()
	{
		for(int i = 0; i < this.getBoxes().size(); i++)
		{
			Box box = this.getBoxes().get(i);
			if(!box.isVisible() && !box.isBomb())
				return;
		}
		
		revealAll();
		demineur.win();
	}
}
