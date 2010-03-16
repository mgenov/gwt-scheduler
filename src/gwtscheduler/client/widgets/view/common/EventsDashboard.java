package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;
import dragndrop.client.core.DragZone;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventPosition;
import org.goda.time.Instant;
import org.goda.time.Interval;
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

    int getCellWidth();

    int getCellHeight();

    int getRowDistance(int startRow, int endRow);
  }

  private Display display;
  private DateGenerator dateGenerator;
  private EventCollisionHelper collisionHelper;
  private final EventBus eventBus;
  private DragZone dragZone;
  private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();

  public EventsDashboard(DateGenerator dateGenerator,EventCollisionHelper collisionHelper, EventBus eventBus, DragZone dragZone) {
    this.dateGenerator = dateGenerator;
    this.collisionHelper = collisionHelper;
    this.eventBus = eventBus;
    this.dragZone = dragZone;
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
  }

  private void clearEvents() {
    for (CalendarEvent event : events) {
      event.removeFromParent(display.asWidget());

    }
    events = new ArrayList<CalendarEvent>();

  }


  
  public void addCalendarEvent(int index, Event event, int rowsCount) {


    int startRow = dateGenerator.getRowForInstant(event.getInterval().getStart().toInstant(), rowsCount);

    int endRow = dateGenerator.getRowForInstant(event.getInterval().getEnd().toInstant(), rowsCount);

               // TODO: will be refactored!

    int height = display.getRowDistance(startRow, endRow);


//    ArrayList<CalendarEvent> collisionEvents = collisionHelper.checkEventsIntervals(events, event);

    int[] position = null;
//    if (collisionEvents.size()==0) {
      position = display.calculateLeftTop(new int[]{startRow, index});
//    }
//    else if(collisionEvents.size())


    
    CalendarEvent calendarEvent = buildCalendarEvent(event, new EventPosition(position[0], position[1]));
    calendarEvent.setSize(display.getCellWidth(), height);
    events.add(calendarEvent);
    new CalendarEventResizeHelper(calendarEvent, display);
    calendarEvent.go(display.asWidget());
  }

  private CalendarEvent buildCalendarEvent(Event event, EventPosition eventPosition) {
    CalendarEvent calendarEvent = new CalendarEvent(event, eventPosition);
    CalendarEvent.Display display = this.display.getCalendarEventDisplay();
    calendarEvent.bindDisplay(display);
    dragZone.add(calendarEvent);
    return calendarEvent;
  }

  public boolean checkForCollision(int[] cell, int cellCount, int rowsCount, CalendarColumn column) {
    int[] end = new int[2];
    end[0] = cell[0]+cellCount;
    end[1] =  cell[1];
    Interval  interval = dateGenerator.getIntervalForRange(cell,end,rowsCount);
      return collisionHelper.checkEventsIntervals(events,interval,column);
  }
}
