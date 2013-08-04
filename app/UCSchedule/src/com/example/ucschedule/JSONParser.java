package com.example.ucschedule;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

/**
 * Class used for parsing JSON data.
 */
public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	protected Context context;
	public JSONParser(Context context){
        this.context = context;
    }
	
	//TODO: This is only sample code.
	public JSONObject getJSONFromUrl(String url) {

		// Making HTTP request
		try {
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();			

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
	
	/**
	 * Gets a JSON object from a file in the \raw direcotry
	 * @return JSONObject
	 * @throws IOException
	 */
	public JSONObject getJSONFromFile() throws IOException
	{
		InputStream is = context.getResources().openRawResource(R.raw.schedule);
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
}
