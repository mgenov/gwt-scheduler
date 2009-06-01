package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.calendar.Interval;

/**
 * Defines event controller operations.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IDateGenerator {


  /**
   * Initializes the date factory.
   * @param interval the interval type
   * @param start the start date
   */
  void init(Interval interval, IDate start);

  /**
   * Gets the current date.
   * @return the current date
   */
  IDate current();

  /**
   * Gets the next date period.
   * @return the next period
   */
  IDateGenerator next();

  /**
   * Gets the previous period.
   * @return the previous period
   */
  IDateGenerator previous();

  /**
   * Creates a new time period.
   * @return the time period
   */
  ITimePeriod period();

}
