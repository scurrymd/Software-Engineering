package com.example.ucschedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

	private static final String MY_ACCOUNT_NAME = "arthur.n.johnson@gmail.com";

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
				
				/*
				try{
				 	Intent intent = new Intent(Intent.ACTION_INSERT);
					intent.setType("vnd.android.cursor.item/event");
					intent.putExtra(Events.TITLE, "");
		
					startActivity(intent);
				}
				catch (ActivityNotFoundException activityNotFound){
					
				}
				*/
				
				addEvent(0, false, 2013, 06, 11, 17, 30, 0, 0, 0, 18, 00, null, "Add Event");
				Intent i = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
				if (i != null)
				startActivity(i);

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
	 * @param semesterEndYear
	 * @param semesterEndMonth
	 * @param semesterEndDay
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
			int semesterEndYear,
			int semesterEndMonth,
			int semesterEndDay,
			int endHour,
			int endMinute,
			String timeZone, 
			String title)
	
	{	
		long eventId = 0;
		int SECOND = 0;
		int MILLISECOND = 0;
		
		String[] projection = new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			Cursor calCursor = getContentResolver().query(
					Calendars.CONTENT_URI,
					projection,
					Calendars.VISIBLE + " = 1", 
					null, 
			        Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
				boolean checkEventAlreadySet = false;
			   do {
			      long id = calCursor.getLong(0);
			      String displayName = calCursor.getString(1);

				    
					Calendar Startcal = new GregorianCalendar(startYear, startMonth, startDay);
					Startcal.setTimeZone(TimeZone.getTimeZone("EST"));
					Startcal.set(Calendar.HOUR, startHour);
					Startcal.set(Calendar.MINUTE, startMinute);
					Startcal.set(Calendar.SECOND, SECOND);
					Startcal.set(Calendar.MILLISECOND, MILLISECOND);
					long start = Startcal.getTimeInMillis();
					
					Calendar Endcal = new GregorianCalendar(startYear, startMonth, startDay);
					Endcal.setTimeZone(TimeZone.getTimeZone("EST"));
					Endcal.set(Calendar.HOUR, endHour);
					Endcal.set(Calendar.MINUTE, endMinute);
					Endcal.set(Calendar.SECOND, SECOND);
					Endcal.set(Calendar.MILLISECOND, MILLISECOND);
					long end = Endcal.getTimeInMillis();
					
					ContentValues values = new ContentValues();
					values.put(Events.DTSTART, start);
					values.put(Events.DTEND, end);
					
					values.put(Events.TITLE, title);
					values.put(Events.EVENT_LOCATION, "Baldwin");
					values.put(Events.CALENDAR_ID, id);

					values.put(Events.EVENT_TIMEZONE, "America/New_York");
					values.put(Events.DESCRIPTION, 
					      "The agenda or some description of the event");
					values.put(Events.ALL_DAY, 0);
					
					Uri uri1 = getContentResolver().insert(Events.CONTENT_URI, values);
					
					eventId = Long.valueOf(uri1.getLastPathSegment());
					
					checkEventAlreadySet = true;
					
					
			   } while (calCursor.moveToNext() && checkEventAlreadySet == false);
			}
		
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
	
	public int testA(int a)
	{
		if(a==1)
		{
			return a;
		}
		else
		{
			return 0;
		}
	}
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
