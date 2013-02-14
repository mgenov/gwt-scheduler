package gwtscheduler.tests.gwt.lasso;

import com.google.gwt.junit.client.GWTTestCase;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.tests.gwt.DateTimeAwarePresenter;
import org.junit.Before;
import org.junit.Test;

import static gwtscheduler.tests.gwt.TestUtils.assertInstantDate;

/**
 * Test case for date factory.
 * @author malp
 */
public class LassoTests extends GWTTestCase {

  DateTimeAwarePresenter subject;

  @Override
  public String getModuleName() {
    return "gwtscheduler.Tests";
  }

  @Before
  @Override
  public void gwtSetUp() {
    subject = new DateTimeAwarePresenter(10, 10);
  }

  @Test
  public void testGetInstantForCell() {
    DateTime i = subject.getInstantForCell(new int[] {0, 0});
    assertInstantDate(i, 2009, 01, 01);
  }

  @Test
  public void testGetInstantForCell2() {
    DateTime i = subject.getInstantForCell(new int[] {1, 0});
    assertInstantDate(i, 2009, 01, 11);
  }

  @Test
  public void testGetInstantForCell3() {
    DateTime i = subject.getInstantForCell(new int[] {1, 3});
    assertInstantDate(i, 2009, 01, 14);
  }

//  @Test
  public void testGetInstantForCell4() {
    //1-14
//    Interval i = subject.getIntervalForRange(new int[] {0, 0}, new int[] {1, 3});
//    int days = i.toDuration().toPeriod(PeriodType.days()).getDays();
//    assertEquals(14, days);
  }

}
