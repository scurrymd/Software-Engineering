package com.example.ucschedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;

public class Event {

	protected Context context;
	public Event(Context context){
        this.context = context;
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
	 * @param location,
	 * @param professor,
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
			String title,
			String location,
			String professor)
	
	{	
		long eventId = 0;
		int SECOND = 0;
		int MILLISECOND = 0;
		
		String[] projection = new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			Cursor calCursor = context.getContentResolver().query(
					Calendars.CONTENT_URI,
					projection,
					Calendars.VISIBLE + " = 1", 
					null, 
			        Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
				boolean checkEventAlreadySet = false;
			   do {
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
					values.put(Events.EVENT_LOCATION, location);
					values.put(Events.CALENDAR_ID, calendarId);

					values.put(Events.EVENT_TIMEZONE, "America/New_York");
					values.put(Events.DESCRIPTION, 
					      professor);
					values.put(Events.ALL_DAY, 0);
					values.put(Events.RRULE,"FREQ=WEEKLY;COUNT=14;");
					
					Uri uri1 = context.getContentResolver().insert(Events.CONTENT_URI, values);
					
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
	 * Gets the first day of the current semester.
	 * 
	 * @param termType
	 * @param termYear
	 * @return int day
	 */
	public int getSemesterStartDay(String termType, int termYear)
	{
		int numMonday=1;
		Calendar c = Calendar.getInstance();
	    c.set( Calendar.YEAR, termYear );
	    if(termType.equals("US"))
	    {
	    	c.set( Calendar.MONTH , Calendar.MAY);
	    }
	    else if(termType.equals("FS"))
	    {
	    	c.set( Calendar.MONTH , Calendar.AUGUST);
	    	numMonday=4;
	    }
	    else	//else if(termType.equals("WS"))
	    {
	    	c.set( Calendar.MONTH , Calendar.JANUARY);
	    }
	    
	    c.set( Calendar.DAY_OF_MONTH, 0 );
	    c.add( Calendar.DAY_OF_MONTH, -1 );

	    System.out.println( c.getTime() );

	    int mondaysCount = 0;

	    while ( mondaysCount != numMonday ) {
	        c.add( Calendar.DAY_OF_MONTH, 1 );
	        if ( c.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) {
	            mondaysCount++; 
	        }       
	    }
	    
		return c.get(Calendar.DATE);
	}
	
	/**
	 * Gets the start month of the current semester.
	 * 
	 * @param termType
	 * @param termYear
	 * @return int startMonth
	 */
	public int getSemesterStartMonth(String termType,int termYear)
	{
		int startMonth=0;
		if(termType.equals("US"))
	    {
	    	startMonth=04;
	    }
	    else if(termType.equals("FS"))
	    {
	    	startMonth=07;
	    }
	    else 	//else if(termType.equals("WS"))
	    {
	    	startMonth=0;
	    }
		
		return startMonth;
	}
}
