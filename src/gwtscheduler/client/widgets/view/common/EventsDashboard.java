package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEventHandler;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventPosition;
import org.goda.time.DateTime;
import org.goda.time.ReadableDateTime;

import java.util.ArrayList;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EventsDashboard {

  public interface Display {

    AbsolutePanel asWidget();

    int[] getCellPosition(int x, int y);

    int[] calculateLeftTop(int[] cellPos);

    CalendarEvent.Display getCalendarEventDisplay();

  }

  private Display display;
  private DateGenerator dateGenerator;
  private EventCollisionHelper collisionHelper;
  private final EventBus eventBus;
  private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

  public EventsDashboard(DateGenerator dateGenerator, EventCollisionHelper collisionHelper, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.collisionHelper = collisionHelper;
    this.eventBus = eventBus;
  }

  public void bindDisplay(final Display display) {
    this.display = display;

    eventBus.addHandler(NavigateNextEvent.TYPE, new NavigateNextEventHandler() {
      @Override
      public void onNavigateNext() {
        clearEvents();
      }
    });

    eventBus.addHandler(NavigatePreviousEvent.TYPE, new NavigatePreviousEventHandler() {
      @Override
      public void onNavigatePrevious() {
        clearEvents();
      }
    });


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
        clearEvents();
      }
    });
//    // handle adding of the newly created event
//    eventBus.addHandler(CalendarEventAddedEvent.TYPE, new CalendarEventAddedHandler() {
//
//      @Override
//      public void onCalendarEventAdded(CalendarEventAddedEvent addEvent) {
//        CalendarEvent event = addEvent.getCalendarEvent();
//        event.go(display.asWidget());
//      }
//    });

  }

  private void clearEvents() {
    for (CalendarEvent event : events) {
      event.removeFromParent(display.asWidget());

    }
    events = new ArrayList<CalendarEvent>();

  }

  public int[] getCell(int x, int y) {
    return display.getCellPosition(x, y);
  }

  public void addCalendarEvent(int index, Event event, int rowsCount) {
    DateTime currentDate = dateGenerator.current();
    DateTime eventDate = event.getInterval().getStart();

    int row = dateGenerator.getRowForInstant(event.getInterval().getStart().toInstant(), rowsCount);

    ArrayList<CalendarEvent> collisionEvents = collisionHelper.checkEventsIntervals(events, event);

    int[] position = null;
    if (collisionEvents.size()==0) {
      position = display.calculateLeftTop(new int[]{row, index});
    }

//

    CalendarEvent calendarEvent = buildCalendarEvent(event, new EventPosition(position[0], position[1]));

    events.add(calendarEvent);

    calendarEvent.go(display.asWidget());
  }

  private CalendarEvent buildCalendarEvent(Event event, EventPosition eventPosition) {
    CalendarEvent calendarEvent = new CalendarEvent(event, eventPosition);
    CalendarEvent.Display display = this.display.getCalendarEventDisplay();
    calendarEvent.bindDisplay(display);
    return calendarEvent;
  }
}
