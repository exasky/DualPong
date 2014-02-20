package com.ups.dualpong.game;

public class Racket {
	protected int x;
	protected int size;
	
	public Racket(int x, int size) {
		this.x = x;
		this.size = size;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
