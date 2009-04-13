package gwtscheduler.junit.common;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.model.DateTime;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;

/**
 * Test case for date operations.
 */
public class DateOperationsTestCase {

	/**
	 * Simple test for date creation.
	 */
	@Test
	public void testCreateDate() {
		IDate date = new DateTime();
		assertNotNull(date);
	}

	/**
	 * Tests date comparison.
	 */
	@Test
	public void testCompareDates() {
		IDate d1 = new DateTime();
		IDate d2 = new DateTime();
		d2.addDays(1);
		assertTrue("d2 should be bigger", d2.compareTo(d1) > 0);
	}

	@Test
	public void testDiffHours1() {
		IDate d1 = new DateTime();
		IDate d2 = d1.copy();
		assertEquals("Diff should be 0", 0, d2.diff(d1).hours());
	}

	@Test
	public void testDiffHours2() {
		IDate d1 = new DateTime();
		IDate d2 = d1.copy().addHours(1);
		assertEquals("Diff should be 1", 1, d2.diff(d1).hours());
	}

	@Test
	public void testDiffDays1() {
		IDate d1 = new DateTime();
		IDate d2 = d1.copy();
		assertEquals("Diff should be 0", 0, d2.diff(d1).days());
	}

	@Test
	public void testDiffDays2() {
		IDate d1 = new DateTime();
		IDate d2 = d1.copy().addDays(1);
		assertEquals("Diff should be 1", 1, d2.diff(d1).days());
	}
}
