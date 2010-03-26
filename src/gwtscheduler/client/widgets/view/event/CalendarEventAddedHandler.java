package gwtscheduler.client.widgets.view.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler for {@link gwtscheduler.client.widgets.view.event.CalendarEventAddedEvent}.
 * 
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface CalendarEventAddedHandler extends EventHandler {


  /**
   * Called when new calendar event has been added.
   * @param event the makeDraggable event that contains new event information
   */
  void onCalendarEventAdded(CalendarEventAddedEvent event);

}
