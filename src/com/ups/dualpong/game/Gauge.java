package com.ups.dualpong.game;

public class Gauge {

	public static final int MIN = 0;
	public static final int MAX = 100;

	protected int current;
	
	public Gauge() {
		this.current = 0;
	}
	
	public Gauge(int current) {
		this.current = current;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

}
