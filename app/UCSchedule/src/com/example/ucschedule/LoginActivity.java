package com.example.ucschedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Class that will display a login screen to log into the UC database.
 * @author Matt
 *	
 */
public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		Button goToMain = (Button) findViewById(R.id.btnGoToMain);
		/**
		 * Button to go to mainActivity
		 */
		Button CheckID = (Button) findViewById(R.id.btnLogin);
		/**
		 * Button to check the username and password entered in the EditText
		 */
		
		goToMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 *  Function to go to the main page
				 */
		 
				Intent n = new Intent(LoginActivity.this,MainActivity.class);
			 	startActivity(n);
			}
		});
		
		CheckID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * function to check the username and password entered in the EditText
				 */
				
		 //TODO: Implement this feature, waiting on UCIT
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
