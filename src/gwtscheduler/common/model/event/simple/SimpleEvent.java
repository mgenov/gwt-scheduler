package gwtscheduler.common.model.event.simple;

import gwtscheduler.common.model.event.Event;
import gwtscheduler.common.model.event.EventType;

import org.goda.time.DateTime;

/**
 * Defines a simple event, ie, an event that does not last more than a day.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class SimpleEvent extends Event {

  /**
   * @param start
   * @param end
   * @param type
   */
  public SimpleEvent(DateTime start, DateTime end, EventType type) {
    super(start, end, type);
  }

  @Override
  protected DateTime filter(DateTime date) {
    return date;
  }
}
