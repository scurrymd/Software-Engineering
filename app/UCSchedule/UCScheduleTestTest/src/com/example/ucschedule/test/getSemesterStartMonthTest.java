package com.example.ucschedule.test;
import junit.framework.TestCase;
import android.content.Context;

import com.example.ucschedule.Event;

public class getSemesterStartMonthTest extends TestCase {
	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testSummerTermCase() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 4 ,testEvent.getSemesterStartMonth("US"));
		//fail("Not yet implemented");
	}
	public void testFallTermType() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 7 ,testEvent.getSemesterStartMonth("FS"));
		//fail("Not yet implemented");
	}
	public void testSpringTermType() {
		Context context = null;
		Event testEvent = new Event(context);
		assertEquals("Good Test Case is valid (not -1)", 0 ,testEvent.getSemesterStartMonth("SS"));
		//fail("Not yet implemented");
	}
}