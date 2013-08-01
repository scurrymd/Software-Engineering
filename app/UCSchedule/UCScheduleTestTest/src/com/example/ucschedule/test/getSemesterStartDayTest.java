package com.example.ucschedule.test;
import junit.framework.TestCase;
import android.content.Context;

import com.example.ucschedule.Event;

public class getSemesterStartDayTest extends TestCase {
	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testSummerTermType() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 6 ,testEvent.getSemesterStartDay("US", 2013));
		//fail("Not yet implemented");
	}
	public void testFallTermType() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 26 ,testEvent.getSemesterStartDay("FS", 2013));
		//fail("Not yet implemented");
	}
	public void testWinterTermType() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 7 ,testEvent.getSemesterStartDay("WS", 2013));
		//fail("Not yet implemented");
	}
	public void testZeroTermYear() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", -1 ,testEvent.getSemesterStartDay("US",0));
		//fail("Not yet implemented");
	}


}
