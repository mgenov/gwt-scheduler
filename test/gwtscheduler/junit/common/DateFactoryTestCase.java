package gwtscheduler.junit.common;

import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.client.utils.GenericDateGenerator;

import org.goda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

/**
 * Test case for date factory.
 * @author malp
 */
public class DateFactoryTestCase {

  DateGenerator dayf = new GenericDateGenerator();
  DateGenerator weekf = new GenericDateGenerator();
  DateGenerator monthf = new GenericDateGenerator();

  DateTime now;

  @Before
  public void initialize() {
    now = new DateTime();

    //    dayf.init(Interval.DAY, now);
    //    weekf.init(Interval.WEEK, now);
    //    monthf.init(Interval.MONTH, now);
  }

  @Test
  public void testDayAdvance() {
    //    DateTime d = dayf.next().current();
    //    Assert.assertEquals(now.addDays(1).day(), d.day());
  }
}
