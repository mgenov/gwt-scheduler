package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.model.TimePeriod;

/**
 * Generic factory.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class GenericDateFactory implements IDateFactory {

  /** the interval type */
  private Interval interval;

  private IDate start, end;

  public IDate current() {
    return start;
  }

  public void init(Interval interval, IDate start) {
    this.interval = interval;
    this.start = start;
    this.end = next();
  }

  public IDate next() {
    switch (interval) {
      case DAY:
        start = start.addDays(1);
        end = start.addDays(1);
      case WEEK:
        start = start.addDays(1);
        end = start.addDays(1);
      case MONTH:
        start = start.addMonths(1);
        end = start.addMonths(1);
      default:
        return start;
    }
  }

  public IDate previous() {
    switch (interval) {
      case DAY:
        start = start.addDays(-1);
        end = start.addDays(-1);
      case WEEK:
        start = start.addDays(-1);
        end = start.addDays(-1);
      case MONTH:
        start = start.addMonths(-1);
        end = start.addMonths(-1);
      default:
        return start;
    }
  }

  public ITimePeriod period() {
    return new TimePeriod(start, end);
  }

}
