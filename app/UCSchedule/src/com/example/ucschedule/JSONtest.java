package com.example.ucschedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
 
public class JSONtest extends ListActivity {
 
    // url to make request
    private static String url = "http://api.androidhive.info/contacts/";
    private static String url2 = "file:///C:/Users/Arthur%20-%20User/Documents/GitHub/Software-Engineering/misc/schedule.json";
     
    // JSON Node names
    public static final String TAG_ACAD_PROGRAM_AREA = "acadProgramArea";
    public static final String TAG_CREDIT_HOURS = "creditHours";
    public static final String TAG_CREDIT_LEVEL_DESC = "creditLevelDesc";
    public static final String TAG_FULL_NAME = "fullName";
    public static final String TAG_NUMBER_OF_ENROLLED_CLASSES = "numberOfEnrolledClasses";
    public static final String TAG_PROGRAM_LOCATION = "programLocation";
    public static final String TAG_STUDENT_CLASSIFICATION = "studentClassification";
    public static final String TAG_TERM_CODE = "termCode";
    
    //Class info
    public static final String TAG_CLASS_INFO = "enrolledClassesInfo";
    public static final String TAG_BUILDING = "buildingCode";
    public static final String TAG_CLASS_CALL_NUMBER = "classCallNumber";
    public static final String TAG_SECTION_NUMBER = "classSectionIDAlpha";
    public static final String TAG_CLASS_TITLE = "classTitle";
    public static final String TAG_COURSE_ID = "courseID";
    public static final String TAG_CREDIT_LEVEL_CODE = "creditLevelCode";
    public static final String TAG_DAYS_OF_WEEK = "daysOfWeek";
    public static final String TAG_GRADE = "grade";
    public static final String TAG_GRADE_TYPE = "gradeType";
    public static final String TAG_GRADE_SHOWN_ON_TRANSCRIPT = "gradeValueShownOnTranscript";
    public static final String TAG_GRADING_SYSTEM_CODE = "gradingSystemCode";
    public static final String TAG_INTRUCT_MEDTHOD_CODE = "instructMethodCode";
    public static final String TAG_INSTRUCTOR_NAME = "instructorName";
    public static final String TAG_START_TIME = "meetingStartTime";
    public static final String TAG_END_TIME = "meetingStopTime";
    public static final String TAG_ROOM_NUMBER = "roomNumber";
    public static final String TAG_SITE_CODE = "siteCode";
    public static final String TAG_TERM_TYPE_SESSION_ID = "termTypeSessionID";
   
 
    // contacts JSONArray
    JSONArray schedule = null;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        
        // TODO: get rid of this code. Need to do it with an Async Task
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
         
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> scheduleList = new ArrayList<HashMap<String, String>>();
 
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from file
        
        JSONObject json=null;
		try {
			json = getJSONFromFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        /*try {
			JSONObject json = getJSONFromFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
 
        try {
            // Getting Array of Contacts
        	schedule = json.getJSONArray(TAG_CLASS_INFO);
             
            // looping through All Contacts
            for(int i = 0; i < schedule.length(); i++){
                JSONObject s = schedule.getJSONObject(i);
                 
                // Storing each json item in variable
                String title = s.getString(TAG_CLASS_TITLE);
                String startTime = s.getString(TAG_START_TIME);
                String endTime = s.getString(TAG_END_TIME);
                 
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
                 
                // adding each child node to HashMap key => value
                map.put(TAG_CLASS_TITLE, title);
                map.put(TAG_START_TIME, startTime);
                map.put(TAG_END_TIME, endTime);
 
                // adding HashList to ArrayList
                scheduleList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
         
         
        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(this, scheduleList,
                R.layout.list_item,
                new String[] { TAG_CLASS_TITLE, TAG_START_TIME, TAG_END_TIME }, new int[] {
                        R.id.name, R.id.email, R.id.mobile });
 
        setListAdapter(adapter);
 
        // selecting single ListView item
        ListView lv = getListView();
 

        /*
        // Launching new screen on Selecting Single ListItem
        lv.setOnItemClickListener(new OnItemClickListener() {
 
        	
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
                String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
                String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
                 
                // Starting new intent
                Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_EMAIL, cost);
                in.putExtra(TAG_PHONE_MOBILE, description);
                startActivity(in);
            }
            
            
        });
        */
    }
    
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