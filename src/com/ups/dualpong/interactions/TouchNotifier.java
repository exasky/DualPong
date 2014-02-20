/**
 * 
 */
package com.ups.dualpong.interactions;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

/**
 * Getting the OnTouch events to call ToucheListeners
 * @author SERIN Kevin
 *
 */
public class TouchNotifier {
	private static final int TIMEOUT = 50;	//200ms without touch => stop touching
	private List<TouchListener> listeners;
	private Handler handler;
	private boolean isTouching;
	
	private Runnable stopTouchRunnable = new Runnable() {
		@Override
		public void run() {
			isTouching = false;
			for (TouchListener listener : listeners) {
				listener.touchUp();
			}
		}
	};
	
	public TouchNotifier() {
		this.listeners = new ArrayList<TouchListener>();
		this.handler = new Handler();
		this.isTouching = false;
	}
	
	public void addListener(TouchListener listener) {
		this.listeners.add(listener);
	}
	
	public void removeListener(TouchListener listener) {
		this.listeners.remove(listener);
	}
	
	/**
	 * Call this method when the user is touching the screen
	 */
	public void touch() {
		if(isTouching) {
			this.handler.removeCallbacks(stopTouchRunnable);	//cancel previous timeout
		}
		else {
			this.isTouching = true;
			for (TouchListener listener : this.listeners) {
				listener.touchDown();
			}
		}
		this.handler.postDelayed(stopTouchRunnable, TIMEOUT);
		
	}
	
	
}
