package gwtscheduler.common.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link gwtscheduler.common.event.CalendarEventAddedEvent}.
 * 
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CalendarEventAddedHandler extends EventHandler {


  /**
   * Called when new calendar event has been added.
   * @param event the add event that contains new event information
   */
  void onCalendarEventAdded(CalendarEventAddedEvent event);

}
