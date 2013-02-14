package gwtscheduler.client.modules.config;


/**
 * Defines application configuration.
 * @author malp
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
   * Gets the line height (in css <em>em</em> units )of the days cells in the
   * day/week view.
   * @return the number of lines that each cell occupies
   */
  int daysLineHeightEMs();

  /**
   * Gets the number of rows for each day.
   * @return the number of rows (ex: 48)
   */
  int rowsInDay();

  /**
   * Gets the number of top rows in the day/week views for month events display.
   * @return
   */
  int getDayViewTopRows();


  /**
   *
   * @return
   */
  int getCalendarHeight();

  /**
   *
   * @return
   */
  int getCalendarWidth();
}
