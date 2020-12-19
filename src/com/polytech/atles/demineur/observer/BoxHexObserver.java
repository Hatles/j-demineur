package com.polytech.atles.demineur.observer;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.demineur.Position;
import com.polytech.atles.hex.HexBoard;
import com.polytech.atles.observer.Observable;

import javafx.scene.paint.Color;

public class BoxHexObserver extends BoxObserver
{
	private HexBoard board;
	private Position pos;
	
	public BoxHexObserver(HexBoard board, Position pos)
	{
		this.board = board;
		this.pos = pos;
	}
	
	@Override
	public void updateView(Observable observable, Object o)
	{
		if(observable instanceof Box)
		{
			Box box = (Box)observable;
			board.getHexGraphic().setText("");
			
			if(box.isVisible())
			{
				if(box.isBomb())
				{
					board.getHexGraphic().setFill(Color.BLACK);
				}
				else
				{
					board.getHexGraphic().setFill(Color.WHITE);
					int b = box.getNeightborBomb();
					if(b != 0)
					{
						board.getHexGraphic().setText(b+"");
						
						switch(b)
						{
						case 1:
							board.getHexGraphic().setFillText(Color.BLUE);
							break;
						case 2:
							board.getHexGraphic().setFillText(Color.GREEN);
							break;
						case 3:
							board.getHexGraphic().setFillText(Color.RED);
							break;
						default:
							board.getHexGraphic().setFillText(Color.PURPLE);
							break;
						}
					}
					else
					{
						board.getHexGraphic().setText("");
					}
				}
			}
			else
			{
				board.getHexGraphic().setText("");
				if(box.isFlag())
				{
					board.getHexGraphic().setFill(Color.RED);
				}
				else
				{
					board.getHexGraphic().setFill(Color.GREY);
				}
			}
			
			board.repaintCell(pos.getX(), pos.getY());
		}
	}
}
