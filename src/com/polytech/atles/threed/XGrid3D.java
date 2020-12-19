package com.polytech.atles.threed;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class XGrid3D extends Xform
{
	private int width;
	private int height;
	private int depth;
	private double ratio;
	
	public XGrid3D(int width, int height, int z, int ratio)
	{
		this.width = width;
		this.height = height;
		this.depth = z;
		this.ratio = ratio;
		
		initGrid();
	}

	private void initGrid()
	{
		final PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setDiffuseColor(Color.DARKRED);
        redMaterial.setSpecularColor(Color.RED);

        final PhongMaterial whiteMaterial = new PhongMaterial();
        whiteMaterial.setDiffuseColor(Color.WHITE);
        whiteMaterial.setSpecularColor(Color.LIGHTBLUE);
        
        final PhongMaterial greyMaterial = new PhongMaterial();
        greyMaterial.setDiffuseColor(Color.DARKGREY);
        greyMaterial.setSpecularColor(Color.GREY);
        
        Cylinder cyl = new Cylinder(1f, height*ratio);
		cyl.setMaterial(whiteMaterial);
        cyl.setTranslateX(- ratio/2);
        cyl.setTranslateY(height*ratio/2 - ratio/2);
        cyl.setTranslateZ(- ratio/2);
        this.getChildren().add(cyl);
        Cylinder cyl1 = new Cylinder(1f, height*ratio);
        cyl1.setMaterial(whiteMaterial);
        cyl1.setTranslateX(- ratio/2);
        cyl1.setTranslateY(height*ratio/2 - ratio/2);
        cyl1.setTranslateZ(depth*ratio- ratio/2);
        this.getChildren().add(cyl1);
        Cylinder cyl2 = new Cylinder(1f, height*ratio);
        cyl2.setMaterial(whiteMaterial);
        cyl2.setTranslateX(width*ratio- ratio/2);
        cyl2.setTranslateY(height*ratio/2 - ratio/2);
        cyl2.setTranslateZ(- ratio/2);
        this.getChildren().add(cyl2);
        Cylinder cyl3 = new Cylinder(1f, height*ratio);
        cyl3.setMaterial(whiteMaterial);
        cyl3.setTranslateX(width*ratio- ratio/2);
        cyl3.setTranslateY(height*ratio/2 - ratio/2);
        cyl3.setTranslateZ(depth*ratio- ratio/2);
        this.getChildren().add(cyl3);
        
        Cylinder cyl4 = new Cylinder(1f, depth*ratio);
        cyl4.setMaterial(whiteMaterial);
        cyl4.setTranslateX(- ratio/2);
        cyl4.setTranslateY(- ratio/2);
        cyl4.setTranslateZ(depth*ratio/2 - ratio/2);
        cyl4.setRotationAxis(Rotate.X_AXIS);
        cyl4.setRotate(90.0);
        this.getChildren().add(cyl4);
        Cylinder cyl5 = new Cylinder(1f, depth*ratio);
        cyl5.setMaterial(whiteMaterial);
        cyl5.setTranslateX(- ratio/2);
        cyl5.setTranslateY(height*ratio - ratio/2);
        cyl5.setTranslateZ(depth*ratio/2 - ratio/2);
        cyl5.setRotationAxis(Rotate.X_AXIS);
        cyl5.setRotate(90.0);
        this.getChildren().add(cyl5);
        Cylinder cyl6 = new Cylinder(1f, depth*ratio);
        cyl6.setMaterial(whiteMaterial);
        cyl6.setTranslateX(width*ratio- ratio/2);
        cyl6.setTranslateY(- ratio/2);
        cyl6.setTranslateZ(depth*ratio/2 - ratio/2);
        cyl6.setRotationAxis(Rotate.X_AXIS);
        cyl6.setRotate(90.0);
        this.getChildren().add(cyl6);
        Cylinder cyl7 = new Cylinder(1f, depth*ratio);
        cyl7.setMaterial(whiteMaterial);
        cyl7.setTranslateX(width*ratio- ratio/2);
        cyl7.setTranslateY(height*ratio - ratio/2);
        cyl7.setTranslateZ(depth*ratio/2 - ratio/2);
        cyl7.setRotationAxis(Rotate.X_AXIS);
        cyl7.setRotate(90.0);
        this.getChildren().add(cyl7);
        
        Cylinder cyl8 = new Cylinder(1f, width*ratio);
        cyl8.setMaterial(whiteMaterial);
        cyl8.setTranslateX(width*ratio/2- ratio/2);
        cyl8.setTranslateY(- ratio/2);
        cyl8.setTranslateZ( - ratio/2);
        cyl8.setRotationAxis(Rotate.Z_AXIS);
        cyl8.setRotate(90.0);
        this.getChildren().add(cyl8);
        Cylinder cyl9 = new Cylinder(1f, width*ratio);
        cyl9.setMaterial(whiteMaterial);
        cyl9.setTranslateX(width*ratio/2- ratio/2);
        cyl9.setTranslateY(height*ratio - ratio/2);
        cyl9.setTranslateZ( - ratio/2);
        cyl9.setRotationAxis(Rotate.Z_AXIS);
        cyl9.setRotate(90.0);
        this.getChildren().add(cyl9);
        Cylinder cyl10 = new Cylinder(1f, width*ratio);
        cyl10.setMaterial(whiteMaterial);
        cyl10.setTranslateX(width*ratio/2- ratio/2);
        cyl10.setTranslateY(- ratio/2);
        cyl10.setTranslateZ( depth*ratio- ratio/2);
        cyl10.setRotationAxis(Rotate.Z_AXIS);
        cyl10.setRotate(90.0);
        this.getChildren().add(cyl10);
        Cylinder cyl11 = new Cylinder(1f, width*ratio);
        cyl11.setMaterial(whiteMaterial);
        cyl11.setTranslateX(width*ratio/2- ratio/2);
        cyl11.setTranslateY(height*ratio - ratio/2);
        cyl11.setTranslateZ(depth*ratio - ratio/2);
        cyl11.setRotationAxis(Rotate.Z_AXIS);
        cyl11.setRotate(90.0);
        this.getChildren().add(cyl11);
	}
	
	public void addElem(int x, int y, int z, Sphere sphere)
	{
		sphere.setTranslateX(x*ratio);
		sphere.setTranslateY(y*ratio);
		sphere.setTranslateZ(z*ratio);
        
        this.getChildren().add(sphere);
	}

	public void addElem(int i, int j, int k, XBox3D box3d)
	{
		box3d.setTranslateX(i*ratio);
		box3d.setTranslateY(j*ratio);
		box3d.setTranslateZ(k*ratio);
        
        this.getChildren().add(box3d);
	}
}
