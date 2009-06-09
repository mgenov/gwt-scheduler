package gwtscheduler.client.interfaces;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;


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
  Interval onNavigateNext();

  /**
   * Goes to the previous series of events.
   * @return the visible time period
   */
  Interval onNavigatePrevious();

  /**
   * Goes to a specified start date.
   * @param date the start date
   * @return the visible time period
   */
  Interval onNavigateTo(ReadableDateTime date);
}
