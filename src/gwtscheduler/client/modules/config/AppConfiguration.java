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

  /**
   * Gets the line height of the days cells in the day/week view.
   * @return the number of lines that each cell occupies
   */
  int getDaysLineHeight();
}
