package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.event.Event;

import java.util.Collection;

/**
 * Defines a view for events. Possible views are: week, month, etc.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IEventView {

	/**
	 * Clears all events in this view.
	 */
	void clearEvents();

	/**
	 * Adds a collection of events.
	 * 
	 * @param events the collection of events to add
	 */
	void addEvents(Collection<Event> events);

	/**
	 * Removes an event from this view.
	 * 
	 * @param event the event to remove
	 * @return <code>true</code> if the event was removed
	 */
	boolean removeEvent(Event event);

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
	 * 
	 * @param date the date
	 */
	void navigateTo(IDate date);

}
