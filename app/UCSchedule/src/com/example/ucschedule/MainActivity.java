package com.example.ucschedule;

import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button goToLogin = (Button) findViewById(R.id.btnGoToLogin);
		/**
		 * Button to go to the login page
		 */
		Button goToCalendar = (Button) findViewById(R.id.btnGoToCalendar);
		/**
		 * Button to open the android calendar
		 */
		Button goToAddEvent = (Button) findViewById(R.id.btnGoToAddEvent);
		/**
		 * Button to open the android calendar
		 */
		
		goToLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 *  Function to go to the login page
				 */
		 
				Intent n = new Intent(MainActivity.this,LoginActivity.class);
			 	startActivity(n);
			}
		});
		
goToAddEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 *  Function to go to the add Event test page
				 */
		 
				Intent n = new Intent(MainActivity.this,EventTestActivity.class);
			 	startActivity(n);
			}
		});
		
		
		goToCalendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * function to open the android calendar
				 */
				Intent i = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
				if (i != null)
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
