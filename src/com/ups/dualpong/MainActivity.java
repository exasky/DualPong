package com.ups.dualpong;

import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.ups.dualpong.bluetooth.BluetoothClient;
import com.ups.dualpong.bluetooth.BluetoothServer;

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

	public void exitMethod(View view) {
		Log.i(classTag, "exit app");
		finish();
	}

//	public void joinMethod(View view){
//		Intent intent=new Intent(this,JoinActivity.class);
//		Log.i(classTag,"join party");
//		startActivity(intent);
//	}
	
	public void creditMethod(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Credits").setMessage(R.string.credit)
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	public void createGame(View view) {
//		BTserver = new BluetoothServer(adapter);
//		BTserver.start();
		startActivity(new Intent(this, CreateActivity.class));
	}

	public void joinGame(View view) {
        startActivity(new Intent(this, JoinActivity.class));
    }
}
