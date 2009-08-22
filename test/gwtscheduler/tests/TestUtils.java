package gwtscheduler.tests;

import static junit.framework.Assert.assertTrue;

public class TestUtils {
  /**
   * Utility method to compare two points.
   * @param p1 the first point
   * @param p2 the second point
   */
  public static void assertEqualPoints(int[] p1, int[] p2) {
    assertTrue("Expected '[" + p1[0] + "," + p1[1] + "]', but got '[" + p2[0]
        + "," + p2[1] + "']", p1[0] == p2[0] && p1[1] == p2[1]);
  }
}
