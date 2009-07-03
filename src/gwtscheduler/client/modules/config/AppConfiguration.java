package gwtscheduler.client.modules.config;

import org.goda.time.DateTimeConstants;

/**
 * Defines application configuration.
 * @author malp
 * @see DateTimeConstants
 */
public interface AppConfiguration {

  /**
   * Gets the start day of the week.
   * @return the start day of the week.
   */
  int startDayOfWeek();

  /**
   * Gets the number of days in a week. Typically it will be <code>7</code>.
   * @return the number of days in a week
   */
  int daysInWeek();
}
