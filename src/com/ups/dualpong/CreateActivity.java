package com.ups.dualpong;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class CreateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

}
