package gwtscheduler.client.widgets.common.navigation;


import gwtscheduler.common.calendar.IntervalType;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Instant;
import gwtscheduler.common.util.Interval;

/**
 * Defines event controller operations.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface DateGenerator {

  /**
   * Initializes the date factory.
   * @param interval the interval type
   * @param start the start date
   */
  void init(IntervalType interval, DateTime start);

  /**
   * Gets the current date.
   * @return the current date
   */
  DateTime current();

  /**
   * Moves to the specified instant.
   * @param start the start date
   */
  void goToDate(DateTime start);

  /**
   * Gets the next date period.
   * @return the next period
   */
  DateGenerator next();

  /**
   * Gets the previous period.
   * @return the previous period
   */
  DateGenerator previous();

  /**
   * Creates a new time period.
   * @return the time period
   */
  Interval interval();

  Interval currentInterval();


  /**
   * Gets the correspondent instant for a cell
   *
   * @param cell the starting cell
   * @param rowsCount the rows count
   */
  DateTime getInstantForCell(int[] cell, int rowsCount);

  /**
   * Gets the correspondent time interval for a given cell range
   *
   * @param start the starting cell
   * @param end the end cell
   * @param  rowsCount teh rows count
   * @return the time interval
   */
  Interval getIntervalForRange(int[] start, int[] end, int rowsCount);

  Interval getIntervalForDate(DateTime date);

  int getRowForInstant(Instant time, int rowsCount);
}
