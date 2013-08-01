package com.example.ucschedule.test;
import junit.framework.TestCase;

import com.example.ucschedule.MainActivity;

public class getSemesterStartMonthTest extends TestCase {
	protected void setUp() throws Exception {
		 super.setUp();
		 }
	protected void tearDown() throws Exception {
		 super.tearDown();
		 }
	public void testSummerTermCase() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 4 ,main.getSemesterStartMonth("US", 2013));
		//fail("Not yet implemented");
	}
	public void testFallTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 7 ,main.getSemesterStartMonth("FS", 2013));
		//fail("Not yet implemented");
	}
	public void testWinterTermType() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", 0 ,main.getSemesterStartMonth("WS", 2013));
		//fail("Not yet implemented");
	}
	public void testZeroTermYear() {
		MainActivity main = new MainActivity();
		assertEquals("Good Test Case is valid (not -1)", -1 ,main.getSemesterStartMonth("US",0));
		//fail("Not yet implemented");
	}
}