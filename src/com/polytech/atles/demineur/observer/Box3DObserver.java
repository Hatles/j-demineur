package com.polytech.atles.demineur.observer;

import com.polytech.atles.demineur.Box;
import com.polytech.atles.observer.Observable;
import com.polytech.atles.threed.XBox3D;

import javafx.scene.Camera;

public class Box3DObserver extends BoxObserver
{
	private XBox3D form;
	
	public Box3DObserver(XBox3D form)
	{
			this.form = form;
	}
	
	@Override
	public void updateView(Observable observable, Object o)
	{
		if(o instanceof Camera)
		{
			form.rotateText((Camera)o);
		}
		if(observable instanceof Box)
		{
			Box box = (Box)observable;
			
			if(box.isVisible())
			{
				if(box.isBomb())
				{
					form.setText(-1);
					
					if(box.isFlag())
						form.setBombFlaged();
					else
					{
						if(box.isLoose())
							form.setBombLoose();
						else
						{
							form.setBomb();
						}
					}
				}
				else
				{
					int b = box.getNeightborBomb();
					form.setText(b);
				}
			}
			else
			{
				form.setText(-1);
				if(box.isFlag())
				{
					form.setFlag();
				}
				else
				{
					form.setHidden();
				}
			}
		}
	}
}
