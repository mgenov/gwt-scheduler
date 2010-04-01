package gwtscheduler.tests.lasso;

import static junit.framework.Assert.*;
import static gwtscheduler.tests.gwt.TestUtils.*;
import gwtscheduler.client.utils.lasso.GenericLassoStrategy;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.tests.mock.TestLassoSubject;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests lasso selection modes.
 * @author malp
 */
public class HorizontalLassoSelectionTests {

  static TestLassoSubject subject;
  static GenericLassoStrategy hStrat;

  @BeforeClass
  public static void setUp() {
    subject = new TestLassoSubject(10, 10);
    hStrat = new HorizontalLassoStrategy();
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
  public void testHorizontalLassoSelectionSimple1() {
    int[] topLeft = {0, 0};
    int[] next = {0, 0};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);
    //same start and end
    assertEquals(2, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {0, 0}, sequences.get(1));
  }

  @Test
  public void testHorizontalLassoSelectionSimple2() {
    int[] topLeft = {0, 0};
    int[] next = {0, 1};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);
    //adjacent cell, same row
    assertEquals(2, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {0, 1}, sequences.get(1));
  }

  @Test
  public void testHorizontalLassoSelectionSimple3() {
    int[] topLeft = {0, 0};
    int[] next = {0, 3};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);
    //adjacent cell, same row
    assertEquals(2, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {0, 3}, sequences.get(1));
  }

  @Test
  public void testHorizontalLassoSelectionSimple4() {
    int[] topLeft = {0, 1};
    int[] next = {2, 3};
    List<int[]> sequences = hStrat.getBlocks(subject, topLeft, next);
    //0,1 - 0,10
    //1,0 - 1, 10
    //2,0 - 2,3
    assertEquals(6, sequences.size());

    assertEqualPoints(new int[] {0, 1}, sequences.get(0));
    assertEqualPoints(new int[] {0, 9}, sequences.get(1));

    assertEqualPoints(new int[] {1, 0}, sequences.get(2));
    assertEqualPoints(new int[] {1, 9}, sequences.get(3));

    assertEqualPoints(new int[] {2, 0}, sequences.get(4));
    assertEqualPoints(new int[] {2, 3}, sequences.get(5));
  }
}
