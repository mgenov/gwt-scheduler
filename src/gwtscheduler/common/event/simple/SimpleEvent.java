package gwtscheduler.common.event.simple;

import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventType;
import gwtscheduler.common.event.IDate;

/**
 * Defines a simple event, ie, an event that does not last more than a day.
 * 
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
    public SimpleEvent(IDate start, IDate end, EventType type) {
        super(start, end, type);
    }

    @Override
    protected IDate filter(IDate date) {
        return date;
    }
}
