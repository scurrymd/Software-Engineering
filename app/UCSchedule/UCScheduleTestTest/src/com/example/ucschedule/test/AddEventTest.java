package com.example.ucschedule.test;
import com.example.ucschedule.MainActivity;

import junit.framework.TestCase;


public class AddEventTest extends TestCase {

	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testGoodCase() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 0 ,main.addEvent(1, false, 2013, 6, 24, 5, 00, 2013, 6, 24, 5, 30, "American/New_York", "Testing Event Add"));
		//fail("Not yet implemented");
	}
	public void testCalIdisInvalid() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.addEvent(-1, false, 2013, 6, 24, 5, 00, 2013, 6, 24, 5, 30, "American/New_York", "Testing Event Add"));
		//fail("Not yet implemented");
	}
}
