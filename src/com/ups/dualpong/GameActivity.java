/**
 * 
 */
package com.ups.dualpong;

import com.ups.dualpong.graphic.GameView;
import com.ups.dualpong.interactions.InclinationNotifier;
import com.ups.dualpong.interactions.TouchNotifier;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author SERIN Kevin
 *
 */
public class GameActivity extends Activity implements SensorEventListener {
	private TouchNotifier touchNotifier = new TouchNotifier();
	private InclinationNotifier inclinationNotifier = new InclinationNotifier();
	private SensorManager sensorManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_activity);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	//force orientation to portrait
		
		//active accelerometer sensor
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		Sensor orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, orientationSensor, SensorManager.SENSOR_DELAY_NORMAL);
		
		//start the game view 
		GameView gameView = (GameView) findViewById(R.id.gameView1);
		touchNotifier.addListener(gameView);
		inclinationNotifier.addListener(gameView);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		touchNotifier.touch();
		return super.onTouchEvent(event);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		this.inclinationNotifier.sensorEvent(event);
		
	}
}
