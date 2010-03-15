package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventPosition;

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
  private final EventBus eventBus;
  private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

  public EventsDashboard(DateGenerator dateGenerator, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.eventBus = eventBus;
  }

  public void bindDisplay(final Display display) {
    this.display = display;

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

  public int[] getCell(int x, int y) {
    return display.getCellPosition(x, y);
  }

  public void addCalendarEvent(int index, Event event) {
    int row = dateGenerator.getRowForInstant(event.getInterval().getStart().toInstant(), 48);
    int[] position = display.calculateLeftTop(new int[]{row, index});

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
