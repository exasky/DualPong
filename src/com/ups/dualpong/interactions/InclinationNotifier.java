/**
 * 
 */
package com.ups.dualpong.interactions;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.os.SystemClock;

/**
 * Getting Sensor events and call InclinationListener
 * @author SERIN Kevin
 *
 */
public class InclinationNotifier {
	private static final int TIME = 10;	//consider sensors values only any TIME ms
	private List<InclinationListener> listeners;
	private long lastTime;
	
	public InclinationNotifier() {
		this.listeners = new ArrayList<InclinationListener>();
		this.lastTime = SystemClock.elapsedRealtime();
	}
	
	public void addListener(InclinationListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(InclinationListener listener) {
		this.listeners.remove(listener);
	}
	
	public void sensorEvent(SensorEvent event) {
		long time = SystemClock.elapsedRealtime();
		if(time - this.lastTime >= TIME) {
			if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				float [] values = event.values;
				float orientationX = values[0];
				notifyListeners(calculatePixel(orientationX));
			}
			this.lastTime = time;
		}
	}
	
	private int calculatePixel(float orientationX) {
		return Math.round(orientationX*10);
	}
	
	private void notifyListeners(int value) {
		for (InclinationListener listener : this.listeners) {
			if(value < 0 ) {
				listener.inclineRight(Math.abs(value));
			}
			else {
				listener.inclineLeft(value);
			}
		}
	}
	
}
