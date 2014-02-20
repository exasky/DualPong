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
