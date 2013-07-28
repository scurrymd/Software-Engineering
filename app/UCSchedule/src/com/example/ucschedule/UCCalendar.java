package com.example.ucschedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;

public class UCCalendar {

	protected Context context;
	public UCCalendar(Context context){
        this.context = context;
    }
	
	//TODO: Get rid of this global.
	private static final String MY_ACCOUNT_NAME = "arthur.n.johnson@gmail.com";
	
	/**
	 * Checks for the UC Calendar ID of the default Android Calendar. This is required for the addEvent method
	 * @return long CalendarID
	 */
	public long checkForUcCalendarId() { 
		long CalendarId = -1;
		String[] projection = 
			      new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			Cursor calCursor = 
					context.getContentResolver().
			            query(Calendars.CONTENT_URI, 
			                  projection, 
			                  Calendars.VISIBLE + " = 1", 
			                  null, 
			                  Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
			   do {
			      long id = calCursor.getLong(0);
			      String displayName = calCursor.getString(1);
			      if(displayName.equals("UC Schedule"))
			      {
			    	  CalendarId = id;
			    	  return CalendarId;
			      }
			   } while (calCursor.moveToNext());
			   
			}
			return CalendarId;
		}  

	/**
	 * Creates a new calendar called "UC Calendar".
	 * @return CalendarID
	 */
	public long createUCCalendar()
	{
		long calendarId;
		
		ContentValues values = new ContentValues();
		values.put(
		      Calendars.ACCOUNT_NAME, 
		      MY_ACCOUNT_NAME);
		values.put(
		      Calendars.ACCOUNT_TYPE, 
		      CalendarContract.ACCOUNT_TYPE_LOCAL);
		values.put(
		      Calendars.NAME, 
		      "UC Schedule");
		values.put(
		      Calendars.CALENDAR_DISPLAY_NAME, 
		      "UC Schedule");
		values.put(
		      Calendars.CALENDAR_COLOR, 
		      0xffff0000);
		values.put(
		      Calendars.CALENDAR_ACCESS_LEVEL, 
		      Calendars.CAL_ACCESS_OWNER);
		values.put(
		      Calendars.OWNER_ACCOUNT, 
		      "some.account@googlemail.com"); //TODO: Get actual account
		values.put(
		      Calendars.CALENDAR_TIME_ZONE, 
		      "America/New_York");
		Uri.Builder builder = 
		      CalendarContract.Calendars.CONTENT_URI.buildUpon(); 
		builder.appendQueryParameter(
		      Calendars.ACCOUNT_NAME, 
		      "com.UCandroid");
		builder.appendQueryParameter(
		      Calendars.ACCOUNT_TYPE, 
		      CalendarContract.ACCOUNT_TYPE_LOCAL);
		builder.appendQueryParameter(
		      CalendarContract.CALLER_IS_SYNCADAPTER, 
		      "true");
		Uri uri = context.getContentResolver().insert(builder.build(), values);
		calendarId = Long.valueOf(uri.getLastPathSegment());
		
		return calendarId;
		
	}
}
