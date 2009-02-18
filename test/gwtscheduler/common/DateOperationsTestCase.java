package gwtscheduler.common;

import gwtscheduler.common.event.IDate;
import gwtscheduler.common.model.SDate;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Test case for date operations.
 */
public class DateOperationsTestCase {

	/**
	 * Simple test for date creation.
	 */
	@Test
	public void testCreateDate() {
		IDate date = new SDate();
		assertNotNull(date);
	}

	/**
	 * Tests date comparison.
	 */
	@Test
	public void testCompareDates() {
		IDate d1 = new SDate();
		IDate d2 = new SDate();
		d2.addDays(1);
		assertTrue("d2 should be bigger", d2.compareTo(d1) > 0);
	}
}
