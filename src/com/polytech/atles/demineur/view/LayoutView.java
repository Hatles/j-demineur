package com.polytech.atles.demineur.view;

import java.io.IOException;

import com.polytech.atles.demineur.controller.LayoutController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class LayoutView
{
	private JDemineur demineur;
	
	public LayoutView(JDemineur demineur)
	{
		this.demineur = demineur;
	}
	
	public BorderPane showLayout() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GridView.class.getResource("./../design/RootLayout.fxml"));
        BorderPane rootLayout = (BorderPane) loader.load();
        LayoutController controller = loader.getController();
        controller.setJDemineur(demineur);
        
        controller.getMenuHex().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	controller.newGameHex();
            }
        });
        controller.getMenuSquare().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	//controller.setSquareType();
            	controller.newGameSquare();
            }
        });
        controller.getMenu3D().setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	controller.newGame3D();
            }
        });
        
		return rootLayout;
	}
}
