package com.ups.dualpong.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.ups.dualpong.game.Ball;

public class GraphicBall extends Ball implements GraphicElement {
	private int radius;
	private int color;
	private Paint paint;
	
	public GraphicBall(Ball ball) {
		super(ball.getX(), ball.getY(), ball.getSpeed(), ball.getAlpha());
		
		this.radius = 10;
		this.color = Color.WHITE;
		this.paint = new Paint();
		this.paint.setAntiAlias(true);
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
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
		canvas.drawCircle(this.x, this.y, this.radius, this.paint);
	}
	
}
