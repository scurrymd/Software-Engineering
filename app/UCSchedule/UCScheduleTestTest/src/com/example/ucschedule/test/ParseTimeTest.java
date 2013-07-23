package com.example.ucschedule.test;

import junit.framework.TestCase;

import com.example.ucschedule.MainActivity;

public class ParseTimeTest extends TestCase {
	
	//Start of parseTimeForHour
	public void testHourValidCaseAM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 11 ,main.parseTimeForHour("11:30am"));
	}
	
	public void testHourValidCasePM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 23 ,main.parseTimeForHour("11:30pm"));
	}
	
	public void testHourMoreThanTwoDigitsAM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForHour("111:30am"));
	}
	
	public void testHourMoreThanTwoDigitsPM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForHour("111:30pm"));
	}
	
	public void testRandomString() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForHour("123fasdf:Fasaf33am"));
	}
	
	public void testHourOneDigitHourAM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 1 ,main.parseTimeForHour("1:30am"));
	}
	
	public void testHourOneDigitHourPM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 13 ,main.parseTimeForHour("1:30pm"));
	}
	
	public void testHourFirstDigitIsZeroAM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 1 ,main.parseTimeForHour("01:30am"));
	}
	
	public void testHourFirstDigitIsZeroPM() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 13 ,main.parseTimeForHour("01:30pm"));
	}
	
	public void testHourMissingHour() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForHour(":30pm"));
	}
	
	public void testHourMissingcolon() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForHour("1130pm"));
	}
	
	//Start of parseTimeForMinute
	public void testMinuteValidCase1() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 30 ,main.parseTimeForMinute("11:30am"));
	}
	
	public void testMinuteValidCase2() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 26 ,main.parseTimeForMinute("11:26am"));
	}
	
	public void testMinuteMoreThanTwoDigits() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForMinute("11:300am"));
	}
	
	public void testMinuteOneDigit1() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForMinute("11:0am"));
	}
	
	public void testMinuteOneDigit2() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForMinute("11:3am"));
	}	
	
	public void testMinuteMissingMinutes() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForMinute("11:am"));
	}
	
	public void testMinuteMissingPeriod() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.parseTimeForMinute("11:30"));
	}

}
