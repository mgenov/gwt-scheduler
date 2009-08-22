package gwtscheduler.common.model.event;

import org.goda.time.DateTime;

/**
 * Super class for events.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class Event {

  protected DateTime start;
  protected DateTime end;
  protected EventType type;

  /**
   * Creates a new scheduled event.
   * @param start
   * @param end
   * @param type
   */
  public Event(DateTime start, DateTime end, EventType type) {
    this.start = filter(start);
    this.end = filter(end);
    this.type = type;
  }

  /**
   * Filters dates, removing the unecessary parts.
   * @param date the date to filter
   */
  protected abstract DateTime filter(DateTime date);

}
