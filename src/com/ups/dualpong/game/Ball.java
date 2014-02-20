package com.ups.dualpong.game;

import com.ups.dualpong.engine.BallEngine;

public class Ball {

	protected int x;
	protected int y;
	protected float speed;
	protected float alpha;

	public Ball() {
	}

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

	public byte[] serialize() {
		byte[] ser = new byte[4];
		ser[0] = Integer.valueOf(x).byteValue();
		ser[1] = Integer.valueOf(y).byteValue();
		ser[2] = Float.valueOf(alpha).byteValue();
		ser[3] = Float.valueOf(speed).byteValue();
		return ser;
	}

	public void unserialize(byte[] serialized, int newWidth) {
		float percent = serialized[0];
		this.x = Integer.valueOf((int) percent * newWidth);
		this.y = serialized[1];
		this.alpha = BallEngine.getNewAngleOnDeviceChanged(serialized[2]);
		this.speed = serialized[3];
	}

}
