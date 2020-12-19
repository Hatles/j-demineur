package com.polytech.atles.hex;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class HexGraphic
{
	private GraphicsContext context;
	private Color fill;
	private Color fillText;
	private Color border;
	private String text;

	public HexGraphic(GraphicsContext context)
	{
		this.context = context;
		context.setFont(Font.font ("Verdana", 20));
		context.setTextAlign(TextAlignment.CENTER);
		fill = Color.WHITE;
		fillText = Color.BLACK;
		border = Color.BLACK;
		text = "";
	}

	public void draw(double[] cX, double[] cY, double[] center)
	{
		context.setFill(fill);
		context.fillPolygon(cX, cY, 6);
		context.setFill(border);
		context.strokePolygon(cX, cY, 6);
		context.setFill(fillText);
		context.fillText(text, center[0], center[1]);
	}

	public Color getFill()
	{
		return fill;
	}

	public void setFill(Color fill)
	{
		this.fill = fill;
	}
	
	public void setFillText(Color fill)
	{
		this.fillText = fill;
	}

	public Color getBorder()
	{
		return border;
	}

	public void setBorder(Color border)
	{
		this.border = border;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
}
