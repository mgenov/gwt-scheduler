package gwtscheduler.common.event.allday;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventType;

/**
 * Defines an all-day event.
 * 
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
	public AllDayEvent(IDate start, IDate end, EventType type) {
		super(start, end, type);
	}

	@Override
	protected IDate filter(IDate date) {
		return date;
	}

}
