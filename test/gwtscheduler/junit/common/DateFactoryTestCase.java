package gwtscheduler.junit.common;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.interfaces.IDateFactory.Interval;
import gwtscheduler.client.utils.GenericDateFactory;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.model.DateTime;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Test case for date factory.
 * @author malp
 */
public class DateFactoryTestCase {

  IDateFactory dayf = new GenericDateFactory();
  IDateFactory weekf = new GenericDateFactory();
  IDateFactory monthf = new GenericDateFactory();

  DateTime now;

  @Before
  public void initialize() {
    now = new DateTime();

    dayf.init(Interval.DAY, now);
    weekf.init(Interval.WEEK, now);
    monthf.init(Interval.MONTH, now);
  }

  @Test
  public void testDayAdvance() {
    IDate d = dayf.next();
    Assert.assertEquals(now.addDays(1).day(), d.day());
  }
}
