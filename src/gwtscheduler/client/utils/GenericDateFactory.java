package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.common.calendar.IDate;

/**
 * Generic factory.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class GenericDateFactory implements IDateFactory {
  /** interval type */
  public enum Interval {
    DAY, WEEK, MONTH;
  }

  /** the interval type */
  private final Interval interval;

  /**
   * Creates a new generic factory.
   * 
   * @param inter the interval type
   */
  public GenericDateFactory(Interval inter) {
    this.interval = inter;
  }

  public IDate next(IDate current) {
    switch (interval) {
      case DAY:
        return current.addDays(1);
      case WEEK:
        return current.addDays(7);
      case MONTH:
        return current.addMonths(1);
      default:
        return null;
    }
  }

  public IDate previous(IDate current) {
    switch (interval) {
      case DAY:
        return current.addDays(-1);
      case WEEK:
        return current.addDays(-7);
      case MONTH:
        return current.addMonths(-1);
      default:
        return null;
    }
  }

}
