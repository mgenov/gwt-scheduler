package gwtscheduler.tests.lasso;

import static gwtscheduler.tests.TestUtils.assertEqualPoints;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import gwtscheduler.client.utils.lasso.GenericLassoStrategy;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.tests.mock.SimpleLassoSubject;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests lasso selection modes.
 * @author malp
 */
public class VerticalLassoSelectionTests {

  static SimpleLassoSubject subject;
  static GenericLassoStrategy vStrat;

  @BeforeClass
  public static void setUp() {
    subject = new SimpleLassoSubject(10, 10);
    vStrat = new  VerticalLassoStrategy();
  }


  @Test
  public void testVComparator() {
    assertTrue(vStrat.compare(new int[] {1, 1}, new int[] {1, 1}) == 0);
    assertTrue(vStrat.compare(new int[] {1, 1}, new int[] {1, 2}) < 0);
    assertTrue(vStrat.compare(new int[] {0, 1}, new int[] {1, 0}) > 0);
    assertTrue(vStrat.compare(new int[] {1, 2}, new int[] {1, 1}) > 0);
    assertTrue(vStrat.compare(new int[] {1, 0}, new int[] {0, 1}) < 0);
  }

  @Test
  public void testVerticalLassoSelectionSimple1() {
    int[] topLeft = {0, 0};
    int[] next = {0, 0};
    List<int[]> sequences = vStrat.getBlocks(subject, topLeft, next);
    //same start and end
    assertEquals(2, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {0, 0}, sequences.get(1));
  }

  @Test
  public void testVerticalLassoSelectionSimple2() {
    int[] topLeft = {0, 0};
    int[] next = {0, 1};
    List<int[]> sequences = vStrat.getBlocks(subject, topLeft, next);
    assertEquals(4, sequences.size());
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {9, 0}, sequences.get(1));

    assertEqualPoints(new int[] {0, 1}, sequences.get(2));
    assertEqualPoints(new int[] {0, 1}, sequences.get(3));
  }

  @Test
  public void testVerticalLassoSelectionSimple3() {
    int[] topLeft = {0, 0};
    int[] next = {3, 0};
    List<int[]> sequences = vStrat.getBlocks(subject, topLeft, next);
    //0,0 - 3,0
    assertEquals(2, sequences.size());
    
    assertEqualPoints(new int[] {0, 0}, sequences.get(0));
    assertEqualPoints(new int[] {3, 0}, sequences.get(1));
  }

  @Test
  public void testVerticalLassoSelectionSimple4() {
    int[] topLeft = {0, 1};
    int[] next = {2, 3};
    List<int[]> sequences = vStrat.getBlocks(subject, topLeft, next);
    //0,1 - 9,1
    //0,2 - 9, 2
    //0,3 - 2,3
    assertEquals(6, sequences.size());

    assertEqualPoints(new int[] {0, 1}, sequences.get(0));
    assertEqualPoints(new int[] {9, 1}, sequences.get(1));

    assertEqualPoints(new int[] {0, 2}, sequences.get(2));
    assertEqualPoints(new int[] {9, 2}, sequences.get(3));

    assertEqualPoints(new int[] {0, 3}, sequences.get(4));
    assertEqualPoints(new int[] {2, 3}, sequences.get(5));
  }

}
