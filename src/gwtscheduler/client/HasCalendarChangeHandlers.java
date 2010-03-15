package gwtscheduler.client;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface HasCalendarChangeHandlers extends HasHandlers {

  HandlerRegistration addCalendarChangeHandler(CalendarChangeHandler handler);
}
