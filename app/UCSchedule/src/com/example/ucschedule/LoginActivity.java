package com.example.ucschedule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
		
		Button CheckID = (Button) findViewById(R.id.btnLogin);
		/**
		 * Button to check the username and password entered in the EditText
		 */
		
		//Button to open the android calendar
		Button goToCalendar = (Button) findViewById(R.id.btnGoToCalendar);
		
		CheckID.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * function to check the username and password entered in the EditText
				 */
				
				EditText un = (EditText) findViewById(R.id.etUsername);
				EditText pw = (EditText) findViewById(R.id.etPassword);
				String username = un.getText().toString();
				
				//Get rid of all whitespaces.
				username = username.replaceAll(" +", "");
				String password = pw.getText().toString();
				
				//The username and password are hard-coded for demonstration purposes.
				if(username.equalsIgnoreCase("johns2au") && password.equals("test"))
				{
					//Start JSON listView activity if the credentials match.
					Intent n = new Intent(LoginActivity.this,JSONtest.class);
				 	startActivity(n);
				}
				else
				{
					//Display an error message if the credentials do not match.
					TextView invalidCredentials = (TextView) findViewById(R.id.tvInvalidUnPw);
					invalidCredentials.setVisibility(View.VISIBLE);
				}
	
				
		 //TODO: Implement this feature, waiting on UCIT
				
			}
		});
		
		
		goToCalendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = LoginActivity.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
				if (i != null)
				startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public void onPause() {
	    super.onPause();  // Always call the superclass method first

	    finish();
	}

}
