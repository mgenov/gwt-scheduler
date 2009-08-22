package gwtscheduler.client.tests;

import static junit.framework.Assert.*;
import gwtscheduler.client.tests.mock.SimpleLassoSubject;
import gwtscheduler.client.utils.lasso.GenericLassoStrategy;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests lasso selection modes.
 * @author malp
 */
public class LassoSelectionTests {

  static SimpleLassoSubject subject;
  static GenericLassoStrategy hStrat;
  static GenericLassoStrategy vStrat;

  @BeforeClass
  public static void setUp() {
    subject = new SimpleLassoSubject(10, 10);
    hStrat = new HorizontalLassoStrategy();
    vStrat = new HorizontalLassoStrategy();
  }

  @Test
  public void testHComparator() {
    assertTrue(hStrat.compare(new int[] {1, 1}, new int[] {1, 1}) == 0);
    assertTrue(hStrat.compare(new int[] {1, 1}, new int[] {1, 2}) < 0);
    assertTrue(hStrat.compare(new int[] {0, 1}, new int[] {1, 0}) < 0);
    assertTrue(hStrat.compare(new int[] {1, 2}, new int[] {1, 1}) > 0);
    assertTrue(hStrat.compare(new int[] {1, 0}, new int[] {0, 1}) > 0);
  }
  @Test
  public void testVComparator() {
    assertTrue(vStrat.compare(new int[] {1, 1}, new int[] {1, 1}) == 0);
    assertTrue(vStrat.compare(new int[] {1, 1}, new int[] {1, 2}) < 0);
    assertTrue(vStrat.compare(new int[] {0, 1}, new int[] {1, 0}) < 0);
    assertTrue(vStrat.compare(new int[] {1, 2}, new int[] {1, 1}) > 0);
    assertTrue(vStrat.compare(new int[] {1, 0}, new int[] {0, 1}) > 0);
  }

  @Test
  public void testHorizontalLassoSelectionSimple1() {
    int[] topLeft = {0, 0};
    int[] next = {0, 0};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);

    assertEquals(1, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
  }

  @Test
  public void testHorizontalLassoSelectionSimple2() {
    int[] topLeft = {0, 0};
    int[] next = {0, 1};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);

    assertEquals(1, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {0, 1}, sequences.get(1));
  }

  /**
   * Utility method to compare two points.
   * @param p1 the first point
   * @param p2 the second point
   */
  void assertEqualPoints(int[] p1, int[] p2) {
    assertEquals("Rows differ", p1[0], p2[0]);
    assertEquals("Columns differ", p1[1], p2[1]);
  }

}
