package com.example.ucschedule;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class FormatTime {
	
	public long convertToMilliseconds(int year, int month, int day, int hour, int minute, int second, int millisecond)
	{
		Calendar cal = new GregorianCalendar(year, month, day);
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		long timeInMilliseconds = cal.getTimeInMillis();
		
		return timeInMilliseconds;
	}

	public long convertToMilliseconds(int year, int month, int day, int hour, int minute, int second)
	{
		Calendar cal = new GregorianCalendar(year, month, day);
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		long timeInMilliseconds = cal.getTimeInMillis();
		
		return timeInMilliseconds;
	}
	
	public long convertToMilliseconds(int year, int month, int day, int hour, int minute)
	{
		Calendar cal = new GregorianCalendar(year, month, day);
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		long timeInMilliseconds = cal.getTimeInMillis();
		
		return timeInMilliseconds;
	}
	
	public long convertToMilliseconds(int year, int month, int day, int hour)
	{
		Calendar cal = new GregorianCalendar(year, month, day);
		cal.setTimeZone(TimeZone.getTimeZone("EST"));
		cal.set(Calendar.HOUR, hour);
		long timeInMilliseconds = cal.getTimeInMillis();
		
		return timeInMilliseconds;
	}
}