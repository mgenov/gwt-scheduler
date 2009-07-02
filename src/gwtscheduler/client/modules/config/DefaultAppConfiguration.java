package gwtscheduler.client.modules.config;

import org.goda.time.DateTimeConstants;

/**
 * Default configuration.
 * @author malp
 */
public class DefaultAppConfiguration implements AppConfiguration {

  public int startDayOfWeek() {
    return DateTimeConstants.MONDAY;
  }

  public int daysInWeek() {
    return 7;
  }

}
