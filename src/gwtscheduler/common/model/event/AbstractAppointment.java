package gwtscheduler.common.model.event;

import org.goda.time.Interval;

/**
 * Super class for events.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class AbstractAppointment {

  protected Interval interval;
  protected EventType type;

  /**
   * Creates a new scheduled event.
   * @param start
   * @param end
   * @param type
   */
  public AbstractAppointment(Interval interval, EventType type) {
    this.interval = filter(interval);
    this.type = type;
  }

  /**
   * Filters dates, removing the unecessary parts.
   * @param date the date to filter
   */
  protected abstract Interval filter(Interval interval);

}
