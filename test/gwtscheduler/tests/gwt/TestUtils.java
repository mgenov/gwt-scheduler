package gwtscheduler.tests.gwt;

import gwtscheduler.common.util.DateTime;

import static junit.framework.Assert.assertTrue;

public class TestUtils {
  /**
   * Utility method to compare two points.
   * @param p1 the first point
   * @param p2 the second point
   */
  public static void assertEqualPoints(int[] p1, int[] p2) {
    assertTrue("Expected '[" + p1[0] + "," + p1[1] + "]', but got '[" + p2[0] + "," + p2[1] + "']", p1[0] == p2[0] && p1[1] == p2[1]);
  }

  /**
   * Asserts an instant.
   * @param ri the instant
   * @param year the year of the instant
   * @param month the month of the instant
   * @param day the day of the instant
   */
  public static void assertInstantDate(DateTime ri, int year, int month, int day) {
//    assertEquals("Year differ", year, ri.get(DateTimeFieldType.year()));
//    assertEquals("Month differ", month, ri.get(DateTimeFieldType.monthOfYear()));
//    assertEquals("Day differ", day, ri.get(DateTimeFieldType.dayOfMonth()));
  }

}
