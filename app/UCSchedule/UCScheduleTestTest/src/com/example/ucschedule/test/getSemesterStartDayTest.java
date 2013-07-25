package com.example.ucschedule.test;
import junit.framework.TestCase;

import com.example.ucschedule.MainActivity;

public class getSemesterStartDayTest extends TestCase {
	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testGoodCase() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 6 ,main.getSemesterStartDay("US", 2013));
		//fail("Not yet implemented");
	}
	public void testEmptyTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.getSemesterStartDay("", 2013));
		//fail("Not yet implemented");
	}
	public void testZeroTermYear() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.getSemesterStartDay("US",0));
		//fail("Not yet implemented");
	}


}
