package com.example.ucschedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.util.Log;
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
		Button goToAddEvent = (Button) findViewById(R.id.btnGoToAddEvent);
		/**
		 * Button to open the android calendar
		 */
		Button goToJsonTest = (Button) findViewById(R.id.btnJsonParseTest);
		
		Button goToAddEventWithJson = (Button) findViewById(R.id.btnAddEventsFromJson);
		
		goToAddEventWithJson.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				JSONArray schedule = null;
				JSONObject json = null;
				
				try {
					json = getJSONFromFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				 try {
					 	String term;
					 	String tempTermYear;
					 	int termYear=0;
					 	String termType=null;
					 	int YEAROFFSET = 2000;
					 	int currentMonth=06;
		                int currentDay=0;
		                int startDay=0;
					 	term= json.getString(TAG_TERM_CODE);
					 	tempTermYear = term.substring(0, term.length()/2);
					 	termYear = Integer.parseInt(tempTermYear);
					 	termYear=termYear+YEAROFFSET;
					 	termType = term.substring(term.length()/2);
					 	startDay=getSemesterStartDay(termType,termYear);
					 	currentMonth=getSemesterStartMonth(termType,termYear);
					 	
					    // Getting Array of Classes
			        	schedule = json.getJSONArray(TAG_CLASS_INFO);
			        	
			            // looping through All Classes
			            for(int i = 0; i < schedule.length(); i++){
			                JSONObject s = schedule.getJSONObject(i);
			                
				            String daysOfWeek= s.getString(TAG_DAYS_OF_WEEK);
				            
				            for(int j=0; j < daysOfWeek.length();j++)
				            {
				                char currentDayOfWeek = daysOfWeek.charAt(j);
				                if (currentDayOfWeek=='M')
				                {
				                	//Monday
				                	currentDay=startDay;
				                }
				                else if (currentDayOfWeek=='T')
				                {
				                	//Tuesday
				                	currentDay=startDay+1;
				                }
				                else if (currentDayOfWeek=='W')
				                {
				                	//Wednesday
				                	currentDay=startDay+2;
				                }
				                else if (currentDayOfWeek=='R')
				                {
				                	//Thursday
				                	currentDay=startDay+3;
				                }
				                else if (currentDayOfWeek=='F')
				                {
				                	//Friday
				                	currentDay=startDay+4;
				                }
				                else
				                {
				                	
				                }
				            	// Storing each json item in variable
				                String title = s.getString(TAG_CLASS_TITLE);
				                
				                //The time coming from the JSON file is missing an "m" for the period (AM or PM).
				                String MISSING_LETTER_IN_PERIOD = "m"; 
				                String startTimeAsString = s.getString(TAG_START_TIME) + MISSING_LETTER_IN_PERIOD; //TODO: check to make sure the letter is missing first
				                String endTimeAsString = s.getString(TAG_END_TIME) + MISSING_LETTER_IN_PERIOD; //TODO: check to make sure the letter is missing first
				                
				                //Hours passed into the calendar have an offset of -1.
				                int HOUR_OFFSET = -1;
				                int startTimeHourAsInt = parseTimeForHour(startTimeAsString) + HOUR_OFFSET;
				                int startTimeMinuteAsInt = parseTimeForMinute(startTimeAsString);
				                
				                int endTimeHourAsInt = parseTimeForHour(endTimeAsString) + HOUR_OFFSET;
				                int endTimeMinuteAsInt = parseTimeForMinute(endTimeAsString);
				                
				                String location= s.getString(TAG_ROOM_NUMBER) + " " + s.getString(TAG_BUILDING);
				                String professor = "Professor: " + s.getString(TAG_INSTRUCTOR_NAME);
				                
				                //TODO: Get rid of duplicate code. Possibly make a seperate method for checking calendar ID.
				                long calId;
								calId = -1;
								calId = checkForUcCalendarId();
								if(calId == -1)
								{
									calId = createCalendar();
								}
								
								//TODO: finish filling in parameters to the AddEvent method.
								//TODO: Get classes to add on proper days of the week.
								addEvent(calId, false, termYear, currentMonth, currentDay, startTimeHourAsInt, startTimeMinuteAsInt, 0, 0, 0, endTimeHourAsInt, endTimeMinuteAsInt, null, title,location,professor);
				            }
				        }
				        Intent calendar = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
				        if (calendar != null)
						startActivity(calendar);
			        } catch (JSONException e) {
			            e.printStackTrace();
			        }
				
				
				
			}
		});
		
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

				long calId;
				calId = -1;
				calId = checkForUcCalendarId();
				if(calId == -1)
				{
					calId = createCalendar();
				}
				addEvent(calId, false, 2013, 06, 11, 17, 30, 0, 0, 0, 18, 00, null, "Add Event","200 Baldwin","Me");
				Intent i = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
				if (i != null)
				startActivity(i);

			}
		});
		
		goToJsonTest.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				/**
				 * function to open the android calendar
				 */
				Intent n = new Intent(MainActivity.this,ScheduleView.class);
			 	startActivity(n);
				
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
			Cursor calCursor = getContentResolver().query(
					Calendars.CONTENT_URI,
					projection,
					Calendars.VISIBLE + " = 1", 
					null, 
			        Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
				boolean checkEventAlreadySet = false;
			   do {
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
					values.put(Events.EVENT_LOCATION, location);
					values.put(Events.CALENDAR_ID, calendarId);

					values.put(Events.EVENT_TIMEZONE, "America/New_York");
					values.put(Events.DESCRIPTION, 
					      professor);
					values.put(Events.ALL_DAY, 0);
					values.put(Events.RRULE,"FREQ=WEEKLY;COUNT=14;");
					
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
	 * Trivial test method used to test the junit tests and code coverage to make sure it's working properly.
	 * @param a
	 * @return
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
	
	/**
	 * Checks for the UC Calendar ID of the default Android Calendar. This is required for the addEvent method
	 * @return long CalendarID
	 */
	private long checkForUcCalendarId() { 
		long CalendarId = -1;
		String[] projection = 
			      new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			Cursor calCursor = 
			      getContentResolver().
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

	private long createCalendar()
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
		Uri uri = 
		      getContentResolver().insert(builder.build(), values);
		calendarId = Long.valueOf(uri.getLastPathSegment());
		
		return calendarId;
		
	}
	
	//TODO: make private and create test cases
	/**
	 * Extracts the hour of a string showing time in the format of "hh:mma" time as an int.
	 * @param time (a string in the form of "hh:mma" where h= hour, m = minute, and a = am or pm)
	 * @return int hour (the hour as an int of the time entered in as a parameter)
	 */
	public int parseTimeForHour(String time)
	{
		try {
			Date date = new SimpleDateFormat("hh:mma", Locale.ENGLISH).parse(time);
			
			@SuppressWarnings("deprecation")
			int hour = date.getHours();
			return hour;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	//TODO: make private and create test cases
	/**
	 * Extracts the minute of a string showing time in the format of "hh:mma" time as an int.
	 * @param time (a string in the form of "hh:mma" where h= hour, m = minute, and a = am or pm)
	 * @return int minute (the minute as an int of the time entered in as a parameter)
	 */
	public int parseTimeForMinute(String time)
	{
		try {
			Date date = new SimpleDateFormat("hh:mma", Locale.ENGLISH).parse(time);
			
			@SuppressWarnings("deprecation")
			int minute = date.getMinutes();
			return minute;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	public static final String TAG_CLASS_INFO = "enrolledClassesInfo";
	public static final String TAG_CLASS_TITLE = "classTitle";
	public static final String TAG_START_TIME = "meetingStartTime";
	public static final String TAG_END_TIME = "meetingStopTime";
	public static final String TAG_DAYS_OF_WEEK = "daysOfWeek";
	public static final String TAG_TERM_CODE = "termCode";
	public static final String TAG_BUILDING = "buildingCode";
	public static final String TAG_ROOM_NUMBER = "roomNumber";
    public static final String TAG_INSTRUCTOR_NAME = "instructorName";
	
	//TODO: Find a way to put this in the JSONParser class and call on it from there.
	public JSONObject getJSONFromFile() throws IOException
	{
		InputStream is = getResources().openRawResource(R.raw.schedule);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		try {
		    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		    int n;
		    while ((n = reader.read(buffer)) != -1) {
		        writer.write(buffer, 0, n);
		    }
		} finally {
		    is.close();
		}

		String jsonString = writer.toString();
		JSONObject jObj=null;
		
		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(jsonString);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
			
		return jObj;
		// return JSON String

	}
}
