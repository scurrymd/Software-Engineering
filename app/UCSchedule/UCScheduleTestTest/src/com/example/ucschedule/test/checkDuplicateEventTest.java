package com.example.ucschedule.test;

import junit.framework.TestCase;

import android.content.Context;

import com.example.ucschedule.Event;
import com.example.ucschedule.UCCalendar;


public class checkDuplicateEventTest extends TestCase {

	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testDefaultCase() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,cal.CheckDuplicateEvent(1, 2013, 05, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testMissingId() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(0, 2013, 05, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testMissingYear() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 0, 05, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testMissingMonth() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 0, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testMissingDay() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 0, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testStartHour() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 6, 0, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testStartMinute() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testEndHour() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 6, 7, 0, 0, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testEndMinute() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 6, 7, 0, 8, 0, "course"));
		//fail("Not yet implemented");
	}
	public void testEmptyTitle() {
		Context context = null;
		UCCalendar cal = new UCCalendar(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,cal.CheckDuplicateEvent(1, 2013, 5, 6, 7, 0, 8, 0, null));
		//fail("Not yet implemented");
	}
	public void testDuplicateEvent() {
		Context context = null;
		Context contextEvent = null;
		UCCalendar cal = new UCCalendar(context);
		Event event = new Event(contextEvent);
		event.addEvent(1, false, 2013, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me", 14);
		assertEquals("Good Test Case is valid (not -1)", 1 ,cal.CheckDuplicateEvent(1, 2013, 05, 24, 5, 0, 5, 30, "Testing Event Add"));
		//fail("Not yet implemented");
	}
}
