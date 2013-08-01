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
	public void testSummerTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 6 ,main.getSemesterStartDay("US", 2013));
		//fail("Not yet implemented");
	}
	public void testFallTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 26 ,main.getSemesterStartDay("FS", 2013));
		//fail("Not yet implemented");
	}
	public void testWinterTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 7 ,main.getSemesterStartDay("WS", 2013));
		//fail("Not yet implemented");
	}
	public void testZeroTermYear() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.getSemesterStartDay("US",0));
		//fail("Not yet implemented");
	}


}
