package com.anoop.testproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button first;
	String TAG = "MainActivity";
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		
		first = (Button)findViewById(R.id.first);
		
	}

	// method to handle button click events
	public void ButtonClicked(View view) {
		if(view == first) {
			Log.d(TAG, "FIRST BUTTON CLICKED");
			Intent cameraIntent = new Intent(this, CameraActivity.class);
			startActivity(cameraIntent);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_layout, menu);
		return true;
	}

}
