package com.polytech.atles.demineur.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.polytech.atles.demineur.GridOption;
import com.polytech.atles.demineur.view.JDemineur;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LayoutController implements Initializable
{
	@FXML
	private RadioMenuItem menuSquare;
	@FXML
	private RadioMenuItem menuHex;
	@FXML
	private RadioMenuItem menuThreeD;

	private JDemineur demineur;
	private GridOption options;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

	}

	public void setJDemineur(JDemineur demineur)
	{
		this.demineur = demineur;
	}

	public RadioMenuItem getMenuSquare()
	{
		return menuSquare;
	}

	public RadioMenuItem getMenuHex()
	{
		return menuHex;
	}

	public void setHexType()
	{
		//demineur.showDemineurOverviewHex();
	}

	public void set3DType()
	{
		//demineur.showDemineur3DView();
	}

	public MenuItem getMenu3D()
	{
		return this.menuThreeD;
	}

	public void newGameSquare()
	{
		GridOption options = getGridOption2D();
		if(options != null)
		{
			Platform.runLater(new Runnable(){

				@Override
				public void run()
				{
					demineur.showDemineurOverview(options);
				}
			});
		}
	}

	public GridOption getGridOption2D()
	{
		options = null;

		// Create the custom dialog.
		Dialog<GridOption> dialog = new Dialog<GridOption>();
		dialog.setTitle("New Game");
		dialog.setHeaderText("Choose options of your next game...");

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new
		// ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType playButtonType = new ButtonType("Play", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(playButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));


		TextField width = new TextField();
		width.setPromptText("Width");
		
		TextField height = new TextField();
		height.setPromptText("Height");
		
		TextField bombs = new TextField();
		bombs.setPromptText("bombs");

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node playButton = dialog.getDialogPane().lookupButton(playButtonType);
		playButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		width.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	width.setText(oldValue);
		        }
		    }
		});
		
		height.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	height.setText(oldValue);
		        }
		    }
		});
		
		bombs.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	bombs.setText(oldValue);
		        }
		    }
		});
		
		BooleanBinding widthValid = Bindings.createBooleanBinding(() -> {
			String text = width.getText();
			
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
			
		}, width.textProperty());

		BooleanBinding heightValid = Bindings.createBooleanBinding(() -> {
			String text = height.getText();
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
		}, height.textProperty());
		
		BooleanBinding bombsValid = Bindings.createBooleanBinding(() -> {
			String text = bombs.getText();
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
		}, bombs.textProperty());
		
		playButton.disableProperty().bind(widthValid.not().or(heightValid.not().or(bombsValid.not())));
		
		grid.add(new Label("Size :"), 0, 0);
		grid.add(width, 1, 0);
		grid.add(height, 2, 0);
		grid.add(new Label("Nb bombs :"), 0, 1);
		grid.add(bombs, 1, 1);

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> width.requestFocus());

		// Convert the result to a username-password-pair when the login button
		// is clicked.
		dialog.setResultConverter(dialogButton ->
		{
			if (dialogButton == playButtonType)
			{
				String w = width.getText();
				int wi = Integer.parseInt(w);
				String h = height.getText();
				int hi = Integer.parseInt(h);
				
				int[] dim = {wi, hi};
				return new GridOption(dim, Integer.parseInt(bombs.getText()));
			}
			return null;
		});

		Optional<GridOption> result = dialog.showAndWait();

		result.ifPresent(usernamePassword ->
		{
			options = result.get();
		});

		return options;
	}
	
	public GridOption getGridOption3D()
	{
		options = null;

		// Create the custom dialog.
		Dialog<GridOption> dialog = new Dialog<GridOption>();
		dialog.setTitle("New Game");
		dialog.setHeaderText("Choose options of your next game...");

		// Set the icon (must be included in the project).
		// dialog.setGraphic(new
		// ImageView(this.getClass().getResource("login.png").toString()));

		// Set the button types.
		ButtonType playButtonType = new ButtonType("Play", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(playButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));


		TextField width = new TextField();
		width.setPromptText("Width");
		
		TextField height = new TextField();
		height.setPromptText("Height");
		
		TextField depth = new TextField();
		depth.setPromptText("Depth");
		
		TextField bombs = new TextField();
		bombs.setPromptText("bombs");

		// Enable/Disable login button depending on whether a username was
		// entered.
		Node playButton = dialog.getDialogPane().lookupButton(playButtonType);
		playButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		width.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	width.setText(oldValue);
		        }
		    }
		});
		
		height.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	height.setText(oldValue);
		        }
		    }
		});
		
		depth.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	depth.setText(oldValue);
		        }
		    }
		});
		
		bombs.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	bombs.setText(oldValue);
		        }
		    }
		});
		
		BooleanBinding widthValid = Bindings.createBooleanBinding(() -> {
			String text = width.getText();
			
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
			
		}, width.textProperty());

		BooleanBinding heightValid = Bindings.createBooleanBinding(() -> {
			String text = height.getText();
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
		}, height.textProperty());
		
		BooleanBinding depthValid = Bindings.createBooleanBinding(() -> {
			String text = depth.getText();
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
		}, depth.textProperty());
		
		BooleanBinding bombsValid = Bindings.createBooleanBinding(() -> {
			String text = bombs.getText();
			if(text.equals(""))
				return false;
			else
			{
			    return text.matches("\\d*");
			}
		}, bombs.textProperty());
		
		playButton.disableProperty().bind(widthValid.not().or(heightValid.not().or(depthValid.not().or(bombsValid.not()))));
		
		grid.add(new Label("Size :"), 0, 0);
		grid.add(width, 1, 0);
		grid.add(height, 2, 0);
		grid.add(depth, 3, 0);
		grid.add(new Label("Nb bombs :"), 0, 1);
		grid.add(bombs, 1, 1);

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> width.requestFocus());

		// Convert the result to a username-password-pair when the login button
		// is clicked.
		dialog.setResultConverter(dialogButton ->
		{
			if (dialogButton == playButtonType)
			{
				String w = width.getText();
				int wi = Integer.parseInt(w);
				String h = height.getText();
				int hi = Integer.parseInt(h);
				String d = depth.getText();
				int di = Integer.parseInt(d);
				
				int[] dim = {wi, hi, di};
				return new GridOption(dim, Integer.parseInt(bombs.getText()));
			}
			return null;
		});

		Optional<GridOption> result = dialog.showAndWait();

		result.ifPresent(usernamePassword ->
		{
			options = result.get();
		});

		return options;
	}

	public void newGameHex()
	{
		GridOption options = getGridOption2D();
		if(options != null)
		{
			Platform.runLater(new Runnable(){

				@Override
				public void run()
				{
					demineur.showDemineurOverviewHex(options);
				}
			});
		}
	}

	public void newGame3D()
	{
		GridOption options = getGridOption3D();
		if(options != null)
		{
			Platform.runLater(new Runnable(){

				@Override
				public void run()
				{
					demineur.showDemineur3DView(options);
				}
			});
		}
	}
}
