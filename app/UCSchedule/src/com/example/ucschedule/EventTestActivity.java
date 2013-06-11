package com.example.ucschedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventtest);
		
		Button goToMain = (Button) findViewById(R.id.btnGoToMainTest);
		/**
		 * Button to go to mainActivity
		 */
		Button AddEvent = (Button) findViewById(R.id.btnAddEvent);
		/**
		 * Button to add event to local calendar
		 */
		
		goToMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 *  Function to go to the main page
				 */
		 
				Intent n = new Intent(EventTestActivity.this,MainActivity.class);
			 	startActivity(n);
			}
		});
		
		AddEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * function to check the username and password entered in the EditText
				 */
				
		 //TODO: Implement this feature, waiting on UCIT
				
			}
		});
	}

}
