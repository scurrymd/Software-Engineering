package com.example.ucschedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Instances;

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
	
	/**
	 * Checks if there is already a duplicate event in the same calendar .
	 * 
	 * @param calId
	 * @param startYear
	 * @param startMonth
	 * @param startDay
	 * @param startHour
	 * @param startMinute
	 * @param endHour
	 * @param endMinute
	 * @param title
	 * @return boolean duplicateEvent
	 */
	public int CheckDuplicateEvent(long calId,int startYear,int startMonth,int startDay,int startHour,int startMinute,int endHour,int endMinute,String title)
	{
		int duplicateEvent=0;
		int SECOND = 0;
		int MILLISECOND = 0;
		
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
		String[] proj = new String[]
		{
			Instances._ID,
			Instances.TITLE,
	        Instances.BEGIN, 
	        Instances.END, 
	    };
		Cursor cursor = 
		Instances.query(context.getContentResolver(), proj, start, end);
		if (cursor.getCount() > 0)
		{
			while(cursor.moveToNext())
			{
				String collisonEvents = cursor.getString(1);
				if(collisonEvents.equals(title))
				{
					duplicateEvent = 1;
					break;
				}
			}				
		}
		return duplicateEvent;
	}
}
