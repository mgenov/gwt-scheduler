package gwtscheduler.common.event;

import gwtscheduler.common.calendar.IDate;

/**
 * Super class for events.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class Event {

	protected IDate start;
	protected IDate end;
	protected EventType type;

	/**
	 * Creates a new scheduled event.
	 * 
	 * @param start
	 * @param end
	 * @param type
	 */
	public Event(IDate start, IDate end, EventType type) {
		this.start = filter(start);
		this.end = filter(end);
		this.type = type;
	}

	/**
	 * Filters dates, removing the unecessary parts.
	 * 
	 * @param date the date to filter
	 */
	protected abstract IDate filter(IDate date);

}
