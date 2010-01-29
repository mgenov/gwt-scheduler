package gwtscheduler.common.model.event;

/**
 * Defines possible types for events.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public enum EventType {

  /**
   * Defines an event that lasts all day.
   */
  ALLDAY,
  /**
   * Defines an event that has does not span more than a day.
   */
  SIMPLE;
}
