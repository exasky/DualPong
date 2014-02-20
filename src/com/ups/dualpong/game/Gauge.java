package com.ups.dualpong.game;

import android.os.SystemClock;
import android.util.Log;


public class Gauge {

	public static final int MIN = 0;
	public static final int MAX = 100;
	
	private static final int TIME = 100;	//min time before increasing or decreasing
	private Long lastTimeIncrease;
	private Long lastTimeDecrease;
	protected int current;
	
	public Gauge() {
		this(0);
	}
	
	public Gauge(int current) {
		this.current = current;
		this.lastTimeIncrease = null;
		this.lastTimeDecrease = null;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}
	
	/**
	 * Increase the value of the gauge according to the time elapsed.
	 */
	public void increase() {
		long currentTime = SystemClock.elapsedRealtime();
		if(this.lastTimeIncrease == null) {
			this.lastTimeDecrease = null;
			Log.d("graphic", ""+MAX);
			this.current = Math.min(this.current+1, MAX);
			this.lastTimeIncrease = currentTime;
		}
		else if(currentTime - this.lastTimeIncrease >= TIME) {
			this.current = Math.min(this.current+1, MAX);
			this.lastTimeIncrease = currentTime;
		}
		
			
	}
	
	/**
	 * Decrease the value of the gauge according to the time elapsed.
	 */
	public void decrease() {
		long currentTime = SystemClock.elapsedRealtime();
		if(this.lastTimeDecrease == null) {
			this.lastTimeIncrease = null;
			this.current = Math.max(this.current-1, MIN);
			this.lastTimeDecrease = currentTime;
		}
		else if(currentTime - this.lastTimeDecrease >= TIME) {
			this.current = Math.max(this.current-1, MIN);
			this.lastTimeDecrease = currentTime;
		}
		
	}

}
