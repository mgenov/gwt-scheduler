package gwtscheduler.junit.common;

import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.DateTime;
import org.goda.time.DateTimeConstants;
import org.goda.time.MutableDateTime;
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
    dayf.init(IntervalType.DAY, now);
    weekf.init(IntervalType.WEEK, now);
    monthf.init(IntervalType.MONTH, now);
  }

  @Test
  public void testMonthIntervals() {
    MutableDateTime mdt = now.toMutableDateTime();
    mdt.setDayOfMonth(10);
    mdt.setYear(2009);
    mdt.setMonthOfYear(DateTimeConstants.JULY);

  }
}
