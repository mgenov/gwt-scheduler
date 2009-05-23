package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

/**
 * Defines event controller operations.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IEventNavigationListener {

  /**
   * Goes to the next series of events.
   * @return the visible time period
   */
  ITimePeriod onNavigateNext();

  /**
   * Goes to the previous series of events.
   * @return the visible time period
   */
  ITimePeriod onNavigatePrevious();

  /**
   * Goes to a specified start date.
   * @param date the start date
   */
  void onNavigateTo(IDate date);
}
