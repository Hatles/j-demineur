package com.polytech.atles.demineur.view;

import java.io.IOException;

import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.Grid3D;
import com.polytech.atles.demineur.GridHex;
import com.polytech.atles.demineur.GridOption;
import com.polytech.atles.demineur.GridSquare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class JDemineur extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	
	//private Button myButton;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Demineur");
        primaryStage.setResizable(false);
        
        initRootLayout();

        int[] dim = {10, 10};
        showDemineurOverview(new GridOption(dim, 10));
        //showDemineur3DView(new GridOption(dim, 5));
        //showDemineurOverviewHex(new GridOption(dim, 5));
        
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            LayoutView view = new LayoutView(this);
            rootLayout = view.showLayout();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //primaryStage.setOnCloseRequest(e -> Platform.exit());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showDemineurView(GridView<?,?> view) {
    	view.showView(rootLayout, primaryStage.getScene());
    	primaryStage.sizeToScene();
	}
    

	public void showDemineurOverview(GridOption gridOption)
	{
		Demineur<GridSquare> game = new Demineur<GridSquare>(gridOption){
			@Override
			protected GridSquare genGrid(GridOption options)
			{
				return new GridSquare(this, options.getDimensions()[1], options.getDimensions()[0], options.getNbBombs());
			}
		};
		GridSquareView view = new GridSquareView(game);
		showDemineurView(view);
	}
	
	public void showDemineurOverviewHex(GridOption gridOption)
	{
		Demineur<GridHex> game = new Demineur<GridHex>(gridOption){
			@Override
			protected GridHex genGrid(GridOption options)
			{
				return new GridHex(this, options.getDimensions()[0], options.getDimensions()[1], options.getNbBombs());
			}
		};
		GridHexView view = new GridHexView(game);
		showDemineurView(view);
	}

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	public void showDemineur3DView(GridOption gridOption)
	{
		Demineur<Grid3D> game = new Demineur<Grid3D>(gridOption){
			@Override
			protected Grid3D genGrid(GridOption options)
			{
				return new Grid3D(this, options.getDimensions()[0], options.getDimensions()[1], options.getDimensions()[2], options.getNbBombs());
			}
		};
		Grid3DView view = new Grid3DView(game);
		showDemineurView(view);
	}

}
