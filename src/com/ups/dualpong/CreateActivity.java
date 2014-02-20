package com.ups.dualpong;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class CreateActivity extends Activity {
	
	public String classTag = "CreateActivity";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create, menu);
		return true;
	}
	
	public void cancelMethod(View view){
		Intent intent=new Intent(this,MainActivity.class);
		Log.i(classTag,"cancel connexion");
		startActivity(intent);
	}
}
