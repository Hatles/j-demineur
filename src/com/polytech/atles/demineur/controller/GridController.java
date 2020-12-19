package com.polytech.atles.demineur.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.Grid;
import com.polytech.atles.executor.ExecutorManager;
import com.polytech.atles.observer.Observer;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public abstract class GridController<G extends Grid<G>> implements Initializable, Observer
{
	@FXML private Button myButton;
	@FXML private Text textTimer;
	@FXML private Text bombCounter;
	
	protected Demineur<G> game;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		assert myButton != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";
		assert textTimer != null : "fx:id=\"textTimer\" was not injected: check your FXML file 'simple.fxml'.";
	}
	
	public void setGame(Demineur<G> game)
	{
		this.game = game;
	}

	public void onBoxClick(Box box)
	{
		ExecutorManager.getInstance().execute(new Runnable(){

			@Override
			public void run()
			{
				game.click(box);
				
			}
		}
		);
	}
	
	public void onBoxLeftClick(Box box)
	{
		ExecutorManager.getInstance().execute(new Runnable(){

			@Override
			public void run()
			{
				game.leftClick(box);
			}
		}
		);
	}
	
	public void updateTimer()
	{
		textTimer.setText(game.getTimer().getTime());
	}
	
	public Button getResetButton()
	{
		return myButton;
	}
	
	public void resetGame()
	{
		ExecutorManager.getInstance().execute(new Runnable(){

			@Override
			public void run()
			{
				game.reset();
			}
		}
		);
	}
	
	public class BoxClickEventHandler implements EventHandler<MouseEvent>
	{
		private Box box;
		private boolean left;
		private boolean right;
		
		public BoxClickEventHandler(int id, Box box)
		{
			this.box = box;
			left = false;
			right = false;
		}
		
		@Override
        public void handle(MouseEvent event) {
			if(event.getEventType() == MouseEvent.MOUSE_PRESSED)
			{
				if (event.isPrimaryButtonDown()) {
					left = true;
		        }
				else
				{
					left = false;
				}
				if (event.isSecondaryButtonDown()) {
					right = true;
		        }
				else
					right = false;
			}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED)
			{
				if(right)
				{
					onBoxLeftClick(box);
					
					right = false;
		        }
				else if (left) {
		            onBoxClick(box);
		            left = false;
		        }
			}
        }
	}
	
	public Text getBombCounter()
	{
		return this.bombCounter;
	}
}


