package com.polytech.atles.demineur.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.polytech.atles.demineur.Grid;
import com.polytech.atles.observer.Observable;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GridGraphicController<G extends Grid<G>> extends GridController<G>
{
	@FXML private Pane container;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		super.initialize(location, resources);
		assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'simple.fxml'.";
	}
	
	@Override
	public void update(Observable observable, Object o)
	{
		// TODO Auto-generated method stub
		
	}
	
	public Pane getContainer()
	{
		return container;
	}
}
