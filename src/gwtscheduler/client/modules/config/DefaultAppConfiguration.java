package gwtscheduler.client.modules.config;

import org.goda.time.DateTimeConstants;

import com.google.inject.Singleton;

/**
 * Default configuration.
 * @author malp
 */
@Singleton
public class DefaultAppConfiguration implements AppConfiguration {

  @Override
  public int startDayOfWeek() {
    return DateTimeConstants.MONDAY;
  }

  @Override
  public int daysInWeek() {
    return 7;
  }

  @Override
  public int daysLineHeightEMs() {
    return 2;
  }

  @Override
  public int rowsInDay() {
    return 48;
  }

}
