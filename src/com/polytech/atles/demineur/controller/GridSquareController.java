package com.polytech.atles.demineur.controller;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.demineur.GridSquare;
import com.polytech.atles.observer.Observable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GridSquareController extends GridController<GridSquare>
{
	@FXML private GridPane gridPane;

	@Override
	public void update(Observable observable, Object o)
	{
	}
	
	public class BoxActionEventHandler implements EventHandler<ActionEvent>
	{
		private Box box;
		
		public BoxActionEventHandler(Box box)
		{
			this.box = box;
		}
		
		@Override
        public void handle(ActionEvent event) {
            onBoxClick(box);
        }
	}
	
	public class BoxLeftClickEventHandler implements EventHandler<MouseEvent>
	{
		private Box box;
		
		public BoxLeftClickEventHandler(Box box)
		{
			this.box = box;
		}

		@Override
		public void handle(MouseEvent event)
		{
			if (event.isSecondaryButtonDown()) {
				onBoxLeftClick(box);
	        }
		}
	}
	
	public GridPane getGridPane()
	{
		return this.gridPane;
	}
}
