package com.ups.dualpong;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.ups.dualpong.bluetooth.BluetoothClient;
import com.ups.dualpong.bluetooth.BluetoothServer;

import java.util.Set;

public class MainActivity extends Activity {
    BluetoothClient BTclient;
    BluetoothServer BTserver;
    BluetoothAdapter adapter;
    BluetoothDevice device;

	public String classTag = "MainActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
        adapter = BluetoothAdapter.getDefaultAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void exitMethod(View view){
		Log.i(classTag, "exit app");
		finish();
	}

    public void createGame(View view){
        BTserver = new BluetoothServer(adapter);
        BTserver.start();
        Set<BluetoothDevice> pairedDevices = adapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            //Je cherche celui dont j'ai besoin (en fait les 2 tel avec lesquels j'ai testé
            if (device.getName().equals("HTC One S")) {
                this.device=device;
            }
        }
        BTclient = new BluetoothClient(device,adapter);
        BTclient.start();
    }

    public void joinGame(View view){

    }
}
