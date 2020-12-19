package com.polytech.atles.demineur.view;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.GridSquare;
import com.polytech.atles.demineur.controller.GridSquareController;
import com.polytech.atles.demineur.observer.BoxSquareObserver;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GridSquareView extends GridView<GridSquare, GridSquareController>
{
	public GridSquareView(Demineur<GridSquare> game)
	{
		super(game, "./../design/SquareGrid.fxml");
	}

	@Override
	protected void initView(Scene scene)
	{
		// gridPane
		int id = 0;
		for(int j = 0; j < game.getGrid().getHeight(); j++)
		{
			for(int i = 0; i < game.getGrid().getWidth(); i++)
			{
				Box box = game.getGrid().getBoxes().get(id);
				
				Button button = new Button();
				button.setMaxSize(40, 40);
				button.setMinSize(40, 40);
				id++;
				
				button.setOnAction(controller.new BoxActionEventHandler(box));
				button.setOnMousePressed(controller.new BoxLeftClickEventHandler(box));
				BoxSquareObserver observer = new BoxSquareObserver(button);
				box.addObserver(observer);
				
				button.getStyleClass().add("box");
				
				GridPane.setConstraints(button, j, i);
				GridPane.setFillWidth(button, true);
				GridPane.setFillHeight(button, true);
				controller.getGridPane().getChildren().add(button);
			}
		}
	}
}
