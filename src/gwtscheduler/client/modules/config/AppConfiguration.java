package gwtscheduler.client.modules.config;

import org.goda.time.DateTimeConstants;

/**
 * Defines application configuration.
 * @author malp
 */
public interface AppConfiguration {

  /**
   * Gets the start day of the week.
   * @return the start day of the week.
   * @see DateTimeConstants
   */
  int getStartDayOfWeek();
}
