package gwtscheduler.common.calendar;

/**
 * Defines a time period.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface ITimePeriod {

  /**
   * Returns the number of hours in this time period.
   * 
   * @return the number of hours
   */
  int hours();

  /**
   * Returns the number of days in this time period.
   * 
   * @return the number of days
   */
  int days();

  /**
   * Returns the number of months in this time period.
   * 
   * @return the number of months
   */
  int months();
}
