package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.IDateGenerator;
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
public class GenericDateGenerator implements IDateGenerator {

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

  public IDateGenerator next() {
    start = start.plusDays(1);
    return this;
  }

  public IDateGenerator previous() {
    start = start.plusDays(-1);
    return this;
  }

  public Interval interval() {
    DateTime end = null;
    if (IntervalType.DAY.equals(interval)) {
      end = start.plusHours(1);
    } else if (IntervalType.WEEK.equals(interval)) {
      end = start.plusDays(7);
    } else if (IntervalType.MONTH.equals(interval)) {
      end = start.plusMonths(1);
    }
    return new Interval(start, end);
  }

}
