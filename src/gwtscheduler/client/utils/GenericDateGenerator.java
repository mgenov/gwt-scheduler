package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

/**
 * Generic factory.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class GenericDateGenerator implements DateGenerator {

  /** the interval type */
  private IntervalType interval;
  /** interval start and end */
  private DateTime start;

  public DateTime current() {
    return start;
  }

  public void init(IntervalType interval, ReadableDateTime start) {
    this.interval = interval;
    this.start = new DateTime(start.getMillis()); //we should maintain a copy of the date
  }

  public DateGenerator next() {
    if (IntervalType.DAY.equals(interval)) {
      start = start.plusDays(1);
    } else if (IntervalType.WEEK.equals(interval)) {
      start = start.plusDays(7);
    } else if (IntervalType.MONTH.equals(interval)) {
      start = start.plusMonths(1);
    }
    return this;
  }

  public DateGenerator previous() {
    if (IntervalType.DAY.equals(interval)) {
      start= start.plusDays(-1);
    } else if (IntervalType.WEEK.equals(interval)) {
      start = start.plusDays(-7);
    } else if (IntervalType.MONTH.equals(interval)) {
      start = start.plusMonths(-1);
    }
    return this;
  }

  public Interval interval() {
    DateTime end = null;
    if (IntervalType.DAY.equals(interval)) {
      end = start.plusDays(1);
    } else if (IntervalType.WEEK.equals(interval)) {
      end = start.plusDays(7);
    } else if (IntervalType.MONTH.equals(interval)) {
      end = start.plusMonths(1);
    }
    return new Interval(start, end);
  }

}
