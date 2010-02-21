package gwtscheduler.tests.gwt.lasso;

import static gwtscheduler.tests.gwt.TestUtils.assertInstantDate;
import gwtscheduler.tests.gwt.DateTimeAwarePresenter;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.PeriodType;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

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
    Instant i = subject.getInstantForCell(new int[] {0, 0});
    assertInstantDate(i, 2009, 01, 01);
  }

  @Test
  public void testGetInstantForCell2() {
    Instant i = subject.getInstantForCell(new int[] {1, 0});
    assertInstantDate(i, 2009, 01, 11);
  }

  @Test
  public void testGetInstantForCell3() {
    Instant i = subject.getInstantForCell(new int[] {1, 3});
    assertInstantDate(i, 2009, 01, 14);
  }

  @Test
  public void testGetInstantForCell4() {
    //1-14
    Interval i = subject.getIntervalForRange(new int[] {0, 0}, new int[] {1, 3});
    int days = i.toDuration().toPeriod(PeriodType.days()).getDays();
    assertEquals(14, days);
  }

}
