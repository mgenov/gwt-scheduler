package gwtscheduler.client.resources.css;

import com.google.gwt.resources.client.ClientBundle;

/**
 * Defines structural css resources. "st" in filename stands for "Structural".
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResourceBundle extends ClientBundle {


  /**
   * This is the main stylesheet for months.
   * @return the month stylesheet resource
   */
  @Source(value = {"scheduler.css"})
  public SchedulerCssResource schedulerCss();

  /**
   * This is the main stylesheet for days and weeks.
   * @return the day/week stylesheet resource
   */
  @Source(value = {"st-common.css", "st-day-week.css"})
  public DayWeekCssResource dayWeekCss();

  /**
   * This is the main stylesheet for months.
   * @return the month stylesheet resource
   */
  @Source(value = {"st-common.css", "st-month.css"})
  public MonthCssResource monthCss();

}
