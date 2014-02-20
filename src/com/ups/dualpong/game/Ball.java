package com.ups.dualpong.game;

public class Ball {

	protected int x;
	protected int y;
	protected float speed;
	protected float alpha;
	
	public Ball(int x, int y, float speed, float alpha) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.alpha = alpha;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}

}
