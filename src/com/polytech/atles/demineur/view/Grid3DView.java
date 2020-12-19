package com.polytech.atles.demineur.view;


import com.polytech.atles.demineur.Demineur;
import com.polytech.atles.demineur.Grid3D;
import com.polytech.atles.demineur.controller.GridController;
import com.polytech.atles.demineur.controller.GridGraphicController;
import com.polytech.atles.demineur.observer.Box3DObserver;
import com.polytech.atles.threed.XBox3D;
import com.polytech.atles.threed.XGrid3D;
import com.polytech.atles.threed.Xform;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

public class Grid3DView extends GridView<Grid3D, GridGraphicController<Grid3D>>
{
	final Group root = new Group();
	final Group axisGroup = new Group();
    final Xform world = new Xform();
    final PerspectiveCamera camera = new PerspectiveCamera(true);
    final Xform cameraXform = new Xform();
    final Xform cameraXform2 = new Xform();
    final Xform cameraXform3 = new Xform();
    final double cameraDistance = 950;
    final Xform moleculeGroup = new Xform();
    
    private Timeline timeline;
    boolean timelinePlaying = false;
    double ONE_FRAME = 1.0/24.0;
    double DELTA_MULTIPLIER = 200.0;
    double CONTROL_MULTIPLIER = 0.1;
    double SHIFT_MULTIPLIER = 0.1;
    double ALT_MULTIPLIER = 0.5;
        
    double mousePosX;
    double mousePosY;
    double mouseOldX;
    double mouseOldY;
    double mouseDeltaX;
    double mouseDeltaY;
    
    private final int width;
	private final int height;
	private final int depth;
	
	private Pane container;
    
	public Grid3DView(Demineur<Grid3D> game)
	{
		super(game, "./../design/GridGraphic.fxml");
		
		width = game.getGrid().getWidth();
		height = game.getGrid().getHeight();
		depth = game.getGrid().getDepth();
	}

	@Override
	protected void initView(Scene scene)
	{
		buildScene();
		buildCamera();
		//buildAxes();
		//buildMolecule();
		buildGrid();
		
		AmbientLight light = new AmbientLight();
		light.setColor(Color.WHITE);
		
		/*PointLight point = new PointLight();
		point.setColor(Color.rgb(50, 100, 50, 1));
		point.setLayoutX(400);
		point.setLayoutY(100);
		point.setTranslateZ(-1100);*/
		
		root.getChildren().addAll(light);
		
		SubScene subScene = new SubScene(root, 800, 600, true, SceneAntialiasing.BALANCED);
		//subScene.setFill(Color.GRAY);
		
		
		container = controller.getContainer();
		container.getChildren().add(subScene);
		subScene.setCamera(camera);
		
		BackgroundImage myBI= new BackgroundImage(new Image(Demineur.class.getResource("./design/image/bg3.jpg").toExternalForm()),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          new BackgroundSize(1, 1, true, true, true, true));
		//then you set to your node
		container.setBackground(new Background(myBI));
		
		handleKeyboard(subScene, world);
        handleMouse(subScene, world);
	}

	private void buildGrid()
	{
		final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
        
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.WHITE);
        greyMaterial.setSpecularColor(Color.WHITE);
        greyMaterial.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/one.png").toExternalForm()));
        
        greyMaterial.setDiffuseColor(Color.DARKGREY);
	    greyMaterial.setSpecularColor(Color.GREY);
	    greyMaterial.setDiffuseMap(new Image("com/polytech/atles/demineur/design/image/one1.png"));
        
        final PhongMaterial greenMaterial = new PhongMaterial();
        greenMaterial.setDiffuseColor(Color.DARKGREEN);
        greenMaterial.setSpecularColor(Color.GREEN);
        int ratio = 50;
		XGrid3D grid = new XGrid3D(width, height, depth, ratio);
		
		int id = 0;
		for(int k = 0; k < depth; k++)
		{
			for(int j = 0; j < height; j++)
			{
				for(int i = 0; i < width; i++)
				{
					com.polytech.atles.demineur.Box box = game.getGrid().getBoxes().get(id);
					
					XBox3D box3D = new XBox3D(50, id);
					
					GridController<Grid3D>.BoxClickEventHandler handler = controller.new BoxClickEventHandler(id, box);
					box3D.setOnMousePressed(handler);
					box3D.setOnMouseClicked(handler);
					
					Box3DObserver observer = new Box3DObserver(box3D);
					box.addObserver(observer);
					
					grid.addElem(i, j, k, box3D);
					id++;
				}
			}
		}
		
		grid.setTranslateX(-width*ratio/2+ratio/2);
		grid.setTranslateY(-height*ratio/2+ratio/2);
		grid.setTranslateZ(-depth*ratio/2+ratio/2);
		
		this.world.getChildren().addAll(grid);
	}

	private void buildScene() {
        //System.out.println("buildScene");
        root.getChildren().add(world);
    }
	
	private void buildCamera() {
        root.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        //cameraXform3.setRotateZ(180.0);
 
        camera.setNearClip(0.1);
        camera.setFarClip(10000.0);
        camera.setTranslateZ(-cameraDistance);
        cameraXform.ry.setAngle(40);
        cameraXform.rx.setAngle(320);
    }
	
	private void handleMouse(SubScene scene, final Node root) {
        container.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        container.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX); 
                mouseDeltaY = (mousePosY - mouseOldY); 
                
                double modifier = 1.0;
                double modifierFactor = 0.1;
                
                if (me.isControlDown()) {
                    modifier = 0.1;
                } 
                if (me.isShiftDown()) {
                    modifier = 10.0;
                }     
                
                if(me.isPrimaryButtonDown() && me.isSecondaryButtonDown())
                {
                	cameraXform2.t.setX(cameraXform2.t.getX() - mouseDeltaX*modifierFactor*modifier*4);  // -
                    cameraXform2.t.setY(cameraXform2.t.getY() - mouseDeltaY*modifierFactor*modifier*4);
                }
                else
                {
	                if (me.isPrimaryButtonDown()) {
	                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX*modifierFactor*modifier*2.0);  // +
	                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY*modifierFactor*modifier*2.0);  // -
	                }
	                else if (me.isSecondaryButtonDown()) {
	                    double z = camera.getTranslateZ();
	                    double newZ = z + mouseDeltaX*modifierFactor*modifier;
	                    camera.setTranslateZ(newZ);
	                }
	                else if (me.isMiddleButtonDown()) {
	                    cameraXform2.t.setX(cameraXform2.t.getX() - mouseDeltaX*modifierFactor*modifier*4);  // -
	                    cameraXform2.t.setY(cameraXform2.t.getY() - mouseDeltaY*modifierFactor*modifier*4);  // -
	                }
                }
            }
        });
        
        container.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
            	
            	double modifier = 1.0;
                double modifierFactor = 0.5;
                
                if (event.isControlDown()) {
                    modifier = 0.1;
                } 
                if (event.isShiftDown()) {
                    modifier = 10.0;
                }
                
            	double z = camera.getTranslateZ();
                double newZ = z + event.getDeltaY()*modifierFactor*modifier;
                camera.setTranslateZ(newZ);
            }
          });
    }
    
    private void handleKeyboard(SubScene scene, final Node root) {
        //final boolean moveCamera = true;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //Duration currentTime;
                switch (event.getCode()) {
                    case Z:
                        if (event.isShiftDown()) {
                            cameraXform.ry.setAngle(0.0);
                            cameraXform.rx.setAngle(0.0);
                            camera.setTranslateZ(-300.0);
                        }   
                        cameraXform2.t.setX(0.0);
                        cameraXform2.t.setY(0.0);
                        break;
                    case X:
                        if (event.isControlDown()) {
                            if (axisGroup.isVisible()) {
                                //System.out.println("setVisible(false)");
                                axisGroup.setVisible(false);
                            }
                            else {
                                //System.out.println("setVisible(true)");
                                axisGroup.setVisible(true);
                            }
                        }   
                        break;
                    case S:
                        if (event.isControlDown()) {
                            if (moleculeGroup.isVisible()) {
                                moleculeGroup.setVisible(false);
                            }
                            else {
                                moleculeGroup.setVisible(true);
                            }
                        }   
                        break;
                    case SPACE:
                        if (timelinePlaying) {
                            timeline.pause();
                            timelinePlaying = false;
                        }
                        else {
                            timeline.play();
                            timelinePlaying = true;
                        }
                        break;
                    case UP:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() - 10.0*CONTROL_MULTIPLIER);  
                        }  
                        else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 10.0*ALT_MULTIPLIER);  
                        }
                        else if (event.isControlDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() - 1.0*CONTROL_MULTIPLIER);  
                        }
                        else if (event.isAltDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() - 2.0*ALT_MULTIPLIER);  
                        }
                        else if (event.isShiftDown()) {
                            double z = camera.getTranslateZ();
                            double newZ = z + 5.0*SHIFT_MULTIPLIER;
                            camera.setTranslateZ(newZ);
                        }
                        break;
                    case DOWN:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() + 10.0*CONTROL_MULTIPLIER);  
                        }  
                        else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 10.0*ALT_MULTIPLIER);  
                        }
                        else if (event.isControlDown()) {
                            cameraXform2.t.setY(cameraXform2.t.getY() + 1.0*CONTROL_MULTIPLIER);  
                        }
                        else if (event.isAltDown()) {
                            cameraXform.rx.setAngle(cameraXform.rx.getAngle() + 2.0*ALT_MULTIPLIER);  
                        }
                        else if (event.isShiftDown()) {
                            double z = camera.getTranslateZ();
                            double newZ = z - 5.0*SHIFT_MULTIPLIER;
                            camera.setTranslateZ(newZ);
                        }
                        break;
                    case RIGHT:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() + 10.0*CONTROL_MULTIPLIER);  
                        }  
                        else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 10.0*ALT_MULTIPLIER);  
                        }
                        else if (event.isControlDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() + 1.0*CONTROL_MULTIPLIER);  
                        }
                        else if (event.isAltDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() - 2.0*ALT_MULTIPLIER);  
                        }
                        break;
                    case LEFT:
                        if (event.isControlDown() && event.isShiftDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() - 10.0*CONTROL_MULTIPLIER);  
                        }  
                        else if (event.isAltDown() && event.isShiftDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 10.0*ALT_MULTIPLIER);  // -
                        }
                        else if (event.isControlDown()) {
                            cameraXform2.t.setX(cameraXform2.t.getX() - 1.0*CONTROL_MULTIPLIER);  
                        }
                        else if (event.isAltDown()) {
                            cameraXform.ry.setAngle(cameraXform.ry.getAngle() + 2.0*ALT_MULTIPLIER);  // -
                        }
                        break;
				default:
					break;
                }
            }
        });
    }
}
