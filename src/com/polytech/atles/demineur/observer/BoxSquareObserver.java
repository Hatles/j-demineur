package com.polytech.atles.demineur.observer;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.observer.Observable;

import javafx.scene.control.Button;

public class BoxSquareObserver extends BoxObserver
{
	private Button button;
	
	public BoxSquareObserver(Button button)
	{
			this.button = button;
	}
	
	@Override
	public void updateView(Observable observable, Object o)
	{
		if(observable instanceof Box)
		{
			Box box = (Box)observable;
			button.getStyleClass().clear();
			button.getStyleClass().add("box");
			
			if(box.isVisible())
			{
				button.getStyleClass().add("visible");
				button.setDisable(true);
				
				if(box.isBomb())
				{
					button.getStyleClass().add("bomb");
				}
				else
				{
					if(box.getNeightborBomb() > 0)
					{
						button.setText(box.getNeightborBomb()+"");
						
						if(box.getNeightborBomb() > 3)
						{
							button.getStyleClass().add("number4");
						}
						else
							button.getStyleClass().add("number"+box.getNeightborBomb());
					}
				}
			}
			else
			{
				button.setDisable(false);
				button.setText("");
				
				if(box.isFlag())
				{
					button.getStyleClass().add("flag");
				}
			}
		}
	}
}
