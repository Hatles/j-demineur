package com.polytech.atles.demineur.view;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.GridHex;
import com.polytech.atles.demineur.Position;
import com.polytech.atles.demineur.controller.GridGraphicController;
import com.polytech.atles.demineur.observer.BoxHexObserver;
import com.polytech.atles.hex.HexBoard;
import com.polytech.atles.hex.HexCellHandler;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class GridHexView extends GridView<GridHex, GridGraphicController<GridHex>>
{
	public GridHexView(Demineur<GridHex> game)
	{
		super(game, "./../design/GridGraphic.fxml");
	}

	@Override
	protected void initView(Scene scene)
	{
		HexBoard board = new HexBoard(game.getGrid().getWidth(), game.getGrid().getHeight(), 20);

		board.setHexCellHandler(new HexCellHandler()
		{
			public void update(int x, int y, MouseEvent event)
			{
				if (event.isPrimaryButtonDown())
				{
					game.click(game.getGrid().getBox(x, y));
				} else if (event.isSecondaryButtonDown())
				{
					game.leftClick(game.getGrid().getBox(x, y));
				}
			}
		});
		
		int id = 0;
		
		for(int j = 0; j < game.getGrid().getHeight(); j++)
		{
			for(int i = 0; i < game.getGrid().getWidth(); i++)
			{
				Box box = game.getGrid().getBoxes().get(id);
				BoxHexObserver observer = new BoxHexObserver(board, new Position(i, j));
				box.addObserver(observer);
				box.notifyObservers(null);
				id++;
			}
		}
		
		board.getHexGraphic().setFill(Color.GREY);
		board.repaint();
		
		controller.getContainer().getChildren().add(board);
	}
}
