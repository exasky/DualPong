package com.ups.dualpong;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.ups.dualpong.bluetooth.BluetoothServer;

public class CreateActivity extends Activity {
    BluetoothServer BTserver ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_activity);

        BluetoothAdapter adaptater = BluetoothAdapter.getDefaultAdapter();
        BTserver = new BluetoothServer(adaptater,this);
        BTserver.start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}
	
	public void cancelMethod(View view){
		Intent intent=new Intent(this,MainActivity.class);
		Log.i("createActivity", "cancel connexion");
		startActivity(intent);
	}

}
