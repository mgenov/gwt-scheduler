package gwtscheduler.client.interfaces;

import gwtscheduler.common.calendar.IDate;

/**
 * Defines event controller operations.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IEventController {

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
	 */
	void navigateTo(IDate date);
}
