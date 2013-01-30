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
  public int getDayViewTopRows() {
    return 3;
  }

  @Override
  public String getCalendarHeight() {
    return "0px";  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public String getCalendarWidth() {
    return "0px";  //To change body of implemented methods use File | Settings | File Templates.
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
