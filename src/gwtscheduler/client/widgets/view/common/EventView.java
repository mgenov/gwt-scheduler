package gwtscheduler.client.widgets.view.common;

import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.util.DateTime;

import java.util.Collection;

/**
 * Defines a view for events. Possible views are: week, month, etc.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface EventView {

  /**
   * Clears all events in this view.
   */
  void clearEvents();

  /**
   * Adds a collection of events.
   * @param events the collection of events to makeDraggable
   */
  void addEvents(Collection<AbstractAppointment> events);

  /**
   * Removes an event from this view.
   * @param event the event to remove
   * @return <code>true</code> if the event was removed
   */
  boolean removeEvent(AbstractAppointment event);

  /**
   * Goes to the next series of events.
   */
  void navigateNext();

  /**
   * Goes to the previous series of events.
   */
  void navigatePrevious();

  /**
   * Goes to a specified start date.
   * @param date the date
   */
  void navigateTo(DateTime date);

}
