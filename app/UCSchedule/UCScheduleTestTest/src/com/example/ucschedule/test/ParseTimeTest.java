package com.example.ucschedule.test;

import junit.framework.TestCase;
import android.content.Context;

import com.example.ucschedule.JSONParser;

public class ParseTimeTest extends TestCase {
	
	//Start of parseTimeForHour
	public void testHourValidCaseAM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 11 ,parse.parseTimeForHour("11:30am"));
	}
	
	public void testHourValidCasePM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 23 ,parse.parseTimeForHour("11:30pm"));
	}
	
	public void testHourMoreThanTwoDigitsAM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForHour("111:30am"));
	}
	
	public void testHourMoreThanTwoDigitsPM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForHour("111:30pm"));
	}
	
	public void testRandomString() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForHour("123fasdf:Fasaf33am"));
	}
	
	public void testHourOneDigitHourAM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 1 ,parse.parseTimeForHour("1:30am"));
	}
	
	public void testHourOneDigitHourPM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 13 ,parse.parseTimeForHour("1:30pm"));
	}
	
	public void testHourFirstDigitIsZeroAM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 1 ,parse.parseTimeForHour("01:30am"));
	}
	
	public void testHourFirstDigitIsZeroPM() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 13 ,parse.parseTimeForHour("01:30pm"));
	}
	
	public void testHourMissingHour() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForHour(":30pm"));
	}
	
	public void testHourMissingcolon() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForHour("1130pm"));
	}
	
	//Start of parseTimeForMinute
	public void testMinuteValidCase1() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 30 ,parse.parseTimeForMinute("11:30am"));
	}
	
	public void testMinuteValidCase2() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", 26 ,parse.parseTimeForMinute("11:26am"));
	}
	
	public void testMinuteMoreThanTwoDigits() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForMinute("11:300am"));
	}
	
	public void testMinuteOneDigit1() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForMinute("11:0am"));
	}
	
	public void testMinuteOneDigit2() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForMinute("11:3am"));
	}	
	
	public void testMinuteMissingMinutes() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForMinute("11:am"));
	}
	
	public void testMinuteMissingPeriod() {
		Context context = null;
		JSONParser parse = new JSONParser(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,parse.parseTimeForMinute("11:30"));
	}

}
