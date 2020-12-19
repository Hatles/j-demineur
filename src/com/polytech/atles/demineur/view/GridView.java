package com.polytech.atles.demineur.view;

import java.io.IOException;

import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.Grid;
import com.polytech.atles.demineur.controller.GridController;
import com.polytech.atles.observer.Observable;
import com.polytech.atles.observer.Observer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public abstract class GridView<G extends Grid<G>, C extends GridController<G>>
{
	protected Demineur<G> game;
	protected C controller;
	private String viewPath;
	
	public GridView(Demineur<G> game, String path)
	{
		this.game = game;
		this.viewPath = path;
	}
	
	public void showView(BorderPane rootLayout, Scene scene)
	{
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GridView.class.getResource(viewPath));
            AnchorPane demineurOverview = (AnchorPane) loader.load();
            demineurOverview.setMaxWidth(1000);
            controller = loader.getController();
            
            controller.setGame(game);
            //init timer observer
            game.getTimer().removeAllObservers();
            game.getTimer().addObserver(new Observer(){
    			@Override
    			public void update(Observable observable, Object o)
    			{
    				controller.updateTimer();
    			}
    		});
            game.getTimer().notifyObservers(null);
            
            game.addObserver(new Observer(){
    			@Override
    			public void update(Observable observable, Object o)
    			{
    				Platform.runLater(new Runnable()
    				{
    					@Override
    					public void run()
    					{
    						updateGameView();
    						
    					}
    				});
    				
    			}
    		});
            game.notifyObservers(null);
            
            //init reset button
            controller.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                	controller.resetGame();
                }
            });
            
            initView(scene);
            
            // Set person overview into the center of root layout.
            rootLayout.setCenter(demineurOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void updateGameView()
	{
		
		controller.getBombCounter().setText(game.getGrid().getBombCount()+"");
		
		if(game.isPlaying())
		{
			controller.getResetButton().setText(":)");
			controller.getResetButton().setTextFill(Color.BLUE);
		}
		
		if(game.getState() == Demineur.State.WAITING)
		{
			controller.getResetButton().setText(":P");
			controller.getResetButton().setTextFill(Color.BLACK);
		}
		
		if(game.getState() == Demineur.State.LOOSE)
		{
			controller.getResetButton().setText("x(");
			controller.getResetButton().setTextFill(Color.RED);
		}
		
		if(game.getState() == Demineur.State.WIN)
		{
			controller.getResetButton().setText(":D");
			controller.getResetButton().setTextFill(Color.GREEN);
		}
	}
	
	protected abstract void initView(Scene scene);
}
