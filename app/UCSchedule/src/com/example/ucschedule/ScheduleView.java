package com.example.ucschedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
 
public class ScheduleView extends ListActivity {
	
	private static ScheduleView instance = null;

    public ScheduleView()
    {
        instance = this;
    }

    public static Context getInstance()
    {
        if (null == instance)
        {
            instance = new ScheduleView();
        }

        return instance;
    }
 
    // contacts JSONArray
    JSONArray schedule = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        
        // TODO: get rid of this code. Need to do it with an Async Task
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        final JSONParser jParser = new JSONParser(getInstance());
         
        Button goToAddEvent = (Button) findViewById(R.id.btnConfirm);
		
		Button goToCancel = (Button) findViewById(R.id.btnCancel);
		
        goToAddEvent.setOnClickListener(new View.OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				
				JSONArray schedule = null;
				JSONObject json = null;
				
				Event event = new Event(getInstance());
				
				try
				{
					json = jParser.getJSONFromFile();
				} 
				catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try 
				{
					String term;
					String tempTermYear;
					int termYear = 0;
					String termType = null;
					int YEAROFFSET = 2000;
					int currentMonth = 06;
					int currentDay = 0;
					int startDay = 0;
					term= json.getString(ScheduleTags.TAG_TERM_CODE);
					tempTermYear = term.substring(0, term.length()/2);
					termYear = Integer.parseInt(tempTermYear);
					termYear=termYear+YEAROFFSET;
					termType = term.substring(term.length()/2);
					startDay=event.getSemesterStartDay(termType,termYear);
					currentMonth=event.getSemesterStartMonth(termType,termYear);
					
					// Getting Array of Classes
					schedule = json.getJSONArray(ScheduleTags.TAG_CLASS_INFO);
					
					// looping through All Classes
					for(int i = 0; i < schedule.length(); i++)
					{
						JSONObject s = schedule.getJSONObject(i);
						
						String daysOfWeek= s.getString(ScheduleTags.TAG_DAYS_OF_WEEK);
						
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
							String title = s.getString(ScheduleTags.TAG_CLASS_TITLE);
							
							//The time coming from the JSON file is missing an "m" for the period (AM or PM).
							String MISSING_LETTER_IN_PERIOD = "m"; 
							String startTimeAsString = s.getString(ScheduleTags.TAG_START_TIME) + MISSING_LETTER_IN_PERIOD; //TODO: check to make sure the letter is missing first
							String endTimeAsString = s.getString(ScheduleTags.TAG_END_TIME) + MISSING_LETTER_IN_PERIOD; //TODO: check to make sure the letter is missing first
							
							UCCalendar cal = new UCCalendar(getInstance());
							
							//Hours passed into the calendar have an offset of -1.
							int HOUR_OFFSET = -1;
							int startTimeHourAsInt = jParser.parseTimeForHour(startTimeAsString) + HOUR_OFFSET;
							int startTimeMinuteAsInt = jParser.parseTimeForMinute(startTimeAsString);
							
							int endTimeHourAsInt = jParser.parseTimeForHour(endTimeAsString) + HOUR_OFFSET;
							int endTimeMinuteAsInt = jParser.parseTimeForMinute(endTimeAsString);
							
							String location= s.getString(ScheduleTags.TAG_ROOM_NUMBER) + " " + s.getString(ScheduleTags.TAG_BUILDING);
							String professor = "Professor: " + s.getString(ScheduleTags.TAG_INSTRUCTOR_NAME);
							
							//TODO: Get rid of duplicate code. Possibly make a seperate method for checking calendar ID.
							long calId;
							calId = -1;
							calId = cal.checkForUcCalendarId();
							if(calId == -1)
							{
								calId = cal.createUCCalendar();
								event.addEvent(calId, false, termYear, currentMonth, currentDay, startTimeHourAsInt, startTimeMinuteAsInt, 0, 0, 0, endTimeHourAsInt, endTimeMinuteAsInt, null, title,location,professor);
							}
							else
							{
								int duplicateEvent=0;
								duplicateEvent=cal.CheckDuplicateEvent(calId,termYear,currentMonth,currentDay,startTimeHourAsInt,startTimeMinuteAsInt,endTimeHourAsInt,endTimeMinuteAsInt,title);
								if(duplicateEvent==0)
								{
									event.addEvent(calId, false, termYear, currentMonth, currentDay, startTimeHourAsInt, startTimeMinuteAsInt, 0, 0, 0, endTimeHourAsInt, endTimeMinuteAsInt, null, title,location,professor);
								}
							}
							
							//TODO: finish filling in parameters to the AddEvent method.
						}
					}
					Intent calendar = ScheduleView.this.getPackageManager().getLaunchIntentForPackage("com.android.calendar");
					if (calendar != null)
					startActivity(calendar);
				}
				catch (JSONException e) 
				{
					e.printStackTrace();
				}	
				
			}
		});

		goToCancel.setOnClickListener(new View.OnClickListener()
		{
		
			@Override
			public void onClick(View v)
			{
				
				Intent n = new Intent(ScheduleView.this,LoginActivity.class);
			 	startActivity(n);
			}
		});
		
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
 
        // getting JSON string from file
        
        JSONObject json=null;
		try 
		{
			json = jParser.getJSONFromFile();
		} 
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 
        try 
		{
            // Getting Array of Contacts
        	schedule = json.getJSONArray(ScheduleTags.TAG_CLASS_INFO);
             
            // looping through All Contacts
            for(int i = 0; i < schedule.length(); i++)
			{
                JSONObject s = schedule.getJSONObject(i);
                 
                // Storing each json item in variable
                String title = s.getString(ScheduleTags.TAG_CLASS_TITLE);
                String startTime = s.getString(ScheduleTags.TAG_START_TIME);
                String endTime = s.getString(ScheduleTags.TAG_END_TIME);
                String daysOfWeek = s.getString(ScheduleTags.TAG_DAYS_OF_WEEK);
                 
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                 
                // adding each child node to HashMap key => value
                map.put(ScheduleTags.TAG_CLASS_TITLE, title);
                map.put(ScheduleTags.TAG_START_TIME, startTime);
                map.put(ScheduleTags.TAG_END_TIME, endTime);
                map.put(ScheduleTags.TAG_DAYS_OF_WEEK, daysOfWeek);
 
                // adding HashList to ArrayList
                scheduleList.add(map);
            }
        } 
		catch (JSONException e) 
		{
            e.printStackTrace();
        }
         
         
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(this, scheduleList,
                R.layout.list_item,
                new String[] { ScheduleTags.TAG_CLASS_TITLE, ScheduleTags.TAG_START_TIME, ScheduleTags.TAG_END_TIME , ScheduleTags.TAG_DAYS_OF_WEEK}, new int[] 
					{
                        R.id.className, R.id.startTime, R.id.endTime, R.id.daysOfWeek 
					});
 
        setListAdapter(adapter);
    }
	
	@Override
	public void onPause() 
	{
	    super.onPause();  // Always call the superclass method first

	    finish();
	}
}