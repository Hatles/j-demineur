package com.polytech.atles.demineur;

import com.polytech.atles.observer.Observable;
import com.polytech.atles.timer.Timer;

public abstract class Demineur<G extends Grid<G>> extends Observable
{
	public static enum State {WAITING, PLAYING, WIN, LOOSE};
	
	private State state;
	private Timer timer;
	private G grid;
	private GridOption options;
	
	public Demineur(GridOption gridOption)
	{
		this.options = gridOption;
		timer = new Timer();
		state = State.WAITING;
		grid = genGrid(options);
		grid.init();
	}
	
	protected abstract G genGrid(GridOption options);

	public void end()
	{
		timer.stop();
		this.notifyObservers(null);
	}
	
	public void win()
	{
		this.setState(State.WIN);
		end();
	}
	
	public void loose()
	{
		this.setState(State.LOOSE);
		end();
	}
	
	public boolean isPlaying()
	{
		return this.state == State.PLAYING;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}
	
	public void click(Box box)
	{
		if(this.isPlaying())
		{
			grid.click(box);
			
			if(this.state != State.LOOSE)
				grid.isWin();
		}
			
		else if(this.state == State.WAITING)
		{
			timer.start();
			this.state = State.PLAYING;
			grid.click(box);
			
			if(this.state != State.LOOSE)
				grid.isWin();
		}
		this.notifyObservers(null);
	}
	
	public void leftClick(Box box)
	{
		if(this.isPlaying())
			grid.leftClick(box);
		this.notifyObservers(null);
	}
	
	public G getGrid()
	{
		return grid;
	}

	public Timer getTimer()
	{
		return timer;
	}

	public void reset()
	{
		this.timer.stop();
		this.timer.initialize();
		
		this.grid.reset();
		state = State.WAITING;
		this.notifyObservers(null);
	}
}
