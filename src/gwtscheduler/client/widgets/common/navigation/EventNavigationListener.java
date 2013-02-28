package gwtscheduler.client.widgets.common.navigation;

import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

/**
 * Defines event controller operations.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface EventNavigationListener {

  /**
   * Goes to the next series of events.
   * @return the visible time period
   */
  Period onNavigateNext();

  /**
   * Goes to the previous series of events.
   * @return the visible time period
   */
  Period onNavigatePrevious();

  /**
   * Goes to a specified start date.
   * @param date the start date
   * @return the visible time period
   */
  Period onNavigateTo(DateTime date);

  /**
   * Gets the current interval.
   * @return the current interval
   */
  Period getCurrentInterval();
}
