package com.example.ucschedule;

import java.util.Calendar;

import android.R.bool;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
/**
 * Main activity class. This contains the main menu of the application.
 * @author Matt
 *	
 */
public class MainActivity extends Activity {

	private static final String MY_ACCOUNT_NAME = null;

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
				
				/*
				Intent n = new Intent(MainActivity.this,EventTestActivity.class);
			 	startActivity(n);
			 	*/
				
				/*
				 * Intent used to access the add event activity in the calendar
				 */
				try{
				 	Intent intent = new Intent(Intent.ACTION_INSERT);
					intent.setType("vnd.android.cursor.item/event");
					intent.putExtra(Events.TITLE, "");
		
					startActivity(intent);
				}
				catch (ActivityNotFoundException activityNotFound){
					
				}

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
	/**
	 * Adds an event to the calendar specified by the calendar ID. Returns the event ID of the event this method creates.
	 * 
	 * @param calendarId
	 * @param allDayEvent
	 * @param startYear
	 * @param startMonth
	 * @param startDay
	 * @param startHour
	 * @param startMinute
	 * @param endYear
	 * @param endMonth
	 * @param endDay
	 * @param endHour
	 * @param endMinute
	 * @param timeZone
	 * @param title
	 * @return long eventID
	 */
	public long addEvent(
			long calendarId, 
			boolean allDayEvent,
			int startYear,
			int startMonth,
			int startDay,
			int startHour,
			int startMinute,
			int endYear,
			int endMonth,
			int endDay,
			int endHour,
			int endMinute,
			String timeZone, 
			String title)
	{
		allDayEvent = false;
		long startMillis = 0; 
		long endMillis = 0;   
		
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(startYear, startMonth, startDay, startHour, startMinute);
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(endYear, endMonth, endDay, endHour, endMinute);
		endMillis = endTime.getTimeInMillis();
		
		/*
		 * TODO: Fill in Calendar Fields 
		 */
				
		long eventId = 0;
		
		/*
		 * TODO: Capture Event ID
		 */
		
		if(eventId != 0)
		{
			return eventId;
		}
		else
			return -1;
	}
	
	/**
	 * Retrieves the calendar ID of the default Android Calendar. This is required for the addEvent method
	 * @return long CalendarID
	 */
	private long getCalendarId() { 
		   String[] projection = new String[]{Calendars._ID}; 
		   String selection = 
		         Calendars.ACCOUNT_NAME + 
		         " = ? " + 
		         Calendars.ACCOUNT_TYPE + 
		         " = ? "; 
		   // use the same values as above:
		   String[] selArgs = 
		         new String[]{
		               MY_ACCOUNT_NAME, 
		               CalendarContract.ACCOUNT_TYPE_LOCAL}; 
		   Cursor cursor = 
		         getContentResolver().
		               query(
		                  Calendars.CONTENT_URI, 
		                  projection, 
		                  selection, 
		                  selArgs, 
		                  null); 
		   if (cursor.moveToFirst()) { 
		      return cursor.getLong(0); 
		   } 
		   return -1; 
		}  

}
