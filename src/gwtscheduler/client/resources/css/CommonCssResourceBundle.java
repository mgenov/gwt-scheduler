package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;

/**
 * Defines structural css resources. "st" in filename stands for "Structural".
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResourceBundle extends ImmutableResourceBundle {

  /**
   * This is the main stylesheet for days and weeks.
   * @return the day/week stylesheet resource
   */
  @Resource(value = {"st-common.css", "st-day-week.css"})
  public DayWeekCssResource dayWeekCss();

  /**
   * This is the main stylesheet for months.
   * @return the month stylesheet resource
   */
  @Resource(value = {"st-common.css", "st-month.css"})
  public MonthCssResource monthCss();

}
