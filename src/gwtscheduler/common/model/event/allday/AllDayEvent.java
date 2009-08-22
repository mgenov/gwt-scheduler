package gwtscheduler.common.model.event.allday;

import gwtscheduler.common.model.event.Event;
import gwtscheduler.common.model.event.EventType;

import org.goda.time.DateTime;

/**
 * Defines an all-day event.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AllDayEvent extends Event {

  /**
   * @param start
   * @param end
   * @param type
   */
  public AllDayEvent(DateTime start, DateTime end, EventType type) {
    super(start, end, type);
  }

  @Override
  protected DateTime filter(DateTime date) {
    return date;
  }

}
