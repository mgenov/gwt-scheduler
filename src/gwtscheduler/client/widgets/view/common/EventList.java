package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.CalendarEventAddedEvent;
import gwtscheduler.common.event.CalendarEventAddedHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EventList {

  interface Display {

    AbsolutePanel asWidget();

  }

  private List<CalendarEvent> events = new ArrayList<CalendarEvent>();
  private Display display;
  private final EventBus eventBus;

  public EventList(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void bindDisplay(Display display) {
    this.display = display;
    
    // handle adding of the newly created event
    eventBus.addHandler(CalendarEventAddedEvent.TYPE, new CalendarEventAddedHandler() {

      @Override
      public void onCalendarEventAdded(CalendarEventAddedEvent addEvent) {
        CalendarEvent event = addEvent.getCalendarEvent();



      }
    });

  }

}
