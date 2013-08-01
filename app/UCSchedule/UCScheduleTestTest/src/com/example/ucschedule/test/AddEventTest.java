package com.example.ucschedule.test;
import junit.framework.TestCase;
import android.content.Context;

import com.example.ucschedule.Event;


public class AddEventTest extends TestCase {

	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testGoodCase() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testCalIdisInvalid() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(-1, false, 2013, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartYearLessThan2013() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2012, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	
	public void testEndYearLessThan2013() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2012, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
		
	public void testEndYearLessThanStartYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2014, 05, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	
	public void testStartYearLessThanEndYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 05, 24, 5, 00, 2014, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
		
	public void testStartMonthGreaterThan12() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 12, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
		
	public void testEndMonthGreaterThan12() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 05, 24, 5, 00, 2013, 12, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
		
	public void testStartMonthLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, -1, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	
	}
	public void testEndMonthLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 05, 24, 5, 00, 2013, -1, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	
	}
	public void testEndMonthLessThanStartMonth() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 05, 24, 5, 00, 2013, 01, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
		
	public void testStartMonthLessThanEndMonth() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 01, 24, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	
	public void testStartDayGreaterThan30() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 00, 31, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayGreaterThan30() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 00, 01, 5, 00, 2013, 00, 31, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayFebruaryGreaterThan27NotLeapYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 01, 28, 5, 00, 2013, 02, 13, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayFebruaryGreaterThan27NotLeapYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 01, 12, 5, 00, 2013, 01, 28, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayFebruaryEqualTo28LeapYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2016, 01, 28, 5, 00, 2016, 02, 13, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayFebruaryEqualTo28LeapYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2016, 00, 29, 5, 00, 2016, 01, 29, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayAprilGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 03, 30, 5, 00, 2013, 05, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayAprilGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 03, 22, 5, 00, 2013, 03, 30, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayJuneGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 05, 30, 5, 00, 2013, 06, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayJuneGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 05, 22, 5, 00, 2013, 05, 30, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDaySeptemberGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 8, 30, 5, 00, 2013, 9, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDaySeptemberGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 8, 22, 5, 00, 2013, 8, 30, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayNovemberGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 30, 5, 00, 2013, 11, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayNovemberGreaterThan29() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 22, 5, 00, 2013, 10, 30, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, -1, 5, 00, 2013, 10, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 22, 5, 00, 2013, 10, -1, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndDayLessThanStartDay() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 25, 5, 00, 2013, 10, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartDayLessThanEndDay() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartHourGreaterThan23() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 24, 00, 2013, 10, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndHourGreaterThan23() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 24, 24, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartHourLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, -1, 00, 2013, 10, 24, 5, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndHourLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 24, -1, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndHourLessThanStartHour() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 23, 4, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndHourGreaterThanStartHour() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartMinuteGreaterThan59() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 60, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndMinuteGreaterThan59() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 23, 6, 60, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testStartMinuteLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, -1, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndMinuteLessThan0() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 5, 00, 2013, 10, 23, 6, -1, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndMinuteLessThanStartMinute() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 6, 30, 2013, 10, 23, 6, 00, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testEndMinuteGreaterThanStartMinute() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.addEvent(1, false, 2013, 10, 23, 6, 00, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testTitleIsEmpty() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 6, 00, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","","Me"));
		//fail("Not yet implemented");
	}
	public void testLocationIsEmpty() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 6, 00, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House",""));
		//fail("Not yet implemented");
	}
	public void testProfessorIsEmpty() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(1, false, 2013, 10, 23, 6, 00, 2013, 10, 23, 6, 30, "American/New_York", "","My House","Me"));
		//fail("Not yet implemented");
	}
	public void testCalendarIdIsNull() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.addEvent(-1, false, 2013, 10, 23, 6, 00, 2013, 10, 23, 6, 30, "American/New_York", "Testing Event Add","My House","Me"));
		//fail("Not yet implemented");
	}
	
}
