/**
 * 
 */
package com.ups.dualpong.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ups.dualpong.game.Gauge;

/**
 * @author SERIN Kevin
 *
 */
public class GraphicGauge extends Gauge implements GraphicElement {
	private int left;
	private int right;
	private int top;
	private int bottom;
	private int color;
	private Paint paint;
	
	public GraphicGauge(Gauge gauge) {
		super();
		this.left = 10;
		this.right = 20;
		this.top = 50;
		this.bottom = 150;
		this.color = Color.WHITE;
		this.paint= new Paint(); 
	}
	
	@Override
	public void drawOnCanvas(Canvas canvas) {
		//borders
		this.paint.setStyle(Paint.Style.STROKE);
		this.paint.setColor(this.color);
		canvas.drawRect(this.left, this.top, this.right, this.bottom, this.paint);
		
		//inside
		int gaugeHeight = this.current * (this.top - this.bottom) / (Gauge.MAX - Gauge.MIN);
		this.paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(left, bottom+gaugeHeight, right, bottom, paint);	
	}

}
