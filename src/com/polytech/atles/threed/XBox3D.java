package com.polytech.atles.threed;

import com.polytech.atles.demineur.Demineur;

import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class XBox3D extends Xform
{
	private Sphere sphere;
	private Box box;
	private Text text;
	
	//private PhongMaterial material;
	
	private final PhongMaterial materialFlag = new PhongMaterial();
	private final PhongMaterial materialHidden = new PhongMaterial();
	private final PhongMaterial materialBomb = new PhongMaterial();
	private final PhongMaterial materialBombL = new PhongMaterial();
	private final PhongMaterial materialBombF = new PhongMaterial();
	
	public XBox3D(float radius, int i)
	{
	    materialFlag.setDiffuseColor(Color.WHITE);
	    materialFlag.setSpecularColor(Color.WHITE);
	    materialFlag.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/flag3d.png").toExternalForm()));
        
	    materialHidden.setDiffuseColor(Color.WHITE);
	    materialHidden.setSpecularColor(Color.WHITE);
	    materialHidden.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/hidden.png").toExternalForm()));
        
	    materialBomb.setDiffuseColor(Color.WHITE);
	    materialBomb.setSpecularColor(Color.WHITE);
	    materialBomb.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/bomb3d.png").toExternalForm()));
        
	    materialBombL.setDiffuseColor(Color.WHITE);
	    materialBombL.setSpecularColor(Color.WHITE);
	    materialBombL.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/bomb3dl.png").toExternalForm()));
        
	    materialBombF.setDiffuseColor(Color.WHITE);
	    materialBombF.setSpecularColor(Color.WHITE);
	    materialBombF.setDiffuseMap(new Image(Demineur.class.getResource("./design/image/bomb3df.png").toExternalForm()));
	    
	    sphere = new Sphere(radius);
	    box = new Box(radius, radius, radius);
	    setMaterial(materialHidden);
	    
	    box.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0)
			{
				onHover();
			}
	    	
	    });
	    box.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0)
			{
				onOut();
			}
	    	
	    });
	   
        
	    text = new Text();
	    text.setFont(Font.font ("Verdana", 20));
	    text.setVisible(false);
	    text.setTextAlignment(TextAlignment.CENTER);
	    text.setTranslateZ(1);
	    
	    final Effect glow = new Glow(20.0);
	    box.setEffect(glow);
	    
        //this.getChildren().add(sphere);
        this.getChildren().add(text);
        this.getChildren().add(box);
	}
	
	public void setGlowEffect(Node node)
	{
		int depth = 70; //Setting the uniform variable for the glow width and height
		 
		DropShadow borderGlow= new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setColor(Color.RED);
		borderGlow.setWidth(depth);
		borderGlow.setHeight(depth);
		
		//node.setEffect(borderGlow);
		node.setEffect(new GaussianBlur());
	}
	
	public void setMaterial(PhongMaterial m)
	{
		sphere.setMaterial(m);
		box.setMaterial(m);
		//material = m;
	}
	
	public void onHover()
	{
		//setMaterialS(greenMaterial);
		//material.setSpecularPower(1);
		//setGlowEffect(box);
		box.setScaleX(0.9);
		box.setScaleY(0.9);
		box.setScaleZ(0.9);
	}
	
	public void onOut()
	{
		//setMaterialS(lastM);
		//material.setSpecularPower(32);
		//box.setEffect(null);
		box.setScaleX(1);
		box.setScaleY(1);
		box.setScaleZ(1);
	}
	
	public void setFlag()
	{
		setMaterial(this.materialFlag);
	}
	
	public void setHidden()
	{
		setMaterial(this.materialHidden);
	}
	
	public void setBomb()
	{
		setMaterial(this.materialBomb);
	}
	
	public void setBombLoose()
	{
		setMaterial(this.materialBombL);
	}
	
	public void setBombFlaged()
	{
		setMaterial(this.materialBombF);
	}
	
	public void setText(int b)
	{
		if(b == -1)
		{
			text.setVisible(false);
			box.setVisible(true);
		}
		else
		{
			text.setVisible(true);
			box.setVisible(false);
			//fade();
			
			if(b != 0)
			{
				text.setText(b+"");
				
				switch(b)
				{
				case 1:
					text.setFill(Color.BLUE);
					break;
				case 2:
					text.setFill(Color.GREEN);
					break;
				case 3:
					text.setFill(Color.RED);
					break;
				default:
					text.setFill(Color.PURPLE);
					break;
				}
			}
			else
			{
				text.setText("");
			}
		}
	}
	
	public void rotateText(Camera cam)
	{
		text.setRotationAxis(Rotate.X_AXIS);
		cam.setRotationAxis(Rotate.X_AXIS);
		text.setRotate(cam.getRotate());
	}
}
