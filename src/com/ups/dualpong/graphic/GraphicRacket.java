/**
 * 
 */
package com.ups.dualpong.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ups.dualpong.game.Racket;

/**
 * @author SERIN Kevin
 *
 */
public class GraphicRacket extends Racket implements GraphicElement {
	private int y;
	private int height;
	private int color;
	private Paint paint;
	
	public GraphicRacket(Racket racket) {
		super(racket.getX(), racket.getSize());
		
		//TODO: change
		this.paint = new Paint();
		this.color = Color.WHITE;
		this.height = 5;
		this.y = 50;	
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public void drawOnCanvas(Canvas canvas) {
		this.paint.setColor(this.color);
		
		int left = this.x - (this.size / 2);
		int right = this.x + (this.size / 2);
		int top = this.y;
		int bottom = this.y + this.height;
		canvas.drawRect(left, top, right, bottom, this.paint);
	}

}
