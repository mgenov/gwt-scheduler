package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import dragndrop.client.core.DragZone;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.calendarevent.HasEventResizeEndHandlers;
import gwtscheduler.client.widgets.view.calendarevent.HasEventResizeStartHandlers;
import gwtscheduler.client.widgets.view.common.resize.EventResizeEndHandler;
import gwtscheduler.client.widgets.view.common.resize.EventResizeStartHandler;
import gwtscheduler.client.widgets.view.common.resize.ResizeHelper;
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

    int getRowDistance(int start, int end);

    HasEventResizeEndHandlers getHasEventResizeEndHandlers();

    HasEventResizeStartHandlers getHasEventResizeStartHandlers();
  }

  private Display display;
  private DateGenerator dateGenerator;
  private EventCollisionHelper collisionHelper;
  private final EventBus eventBus;
  private DragZone dragZone;
  private ResizeHelper resizeHelper;
  private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
  private WidgetResizeHandler displayWidgetResizeHandler;

  public EventsDashboard(DateGenerator dateGenerator, EventCollisionHelper collisionHelper, EventBus eventBus, DragZone dragZone, ResizeHelper resizeHelper) {
    this.dateGenerator = dateGenerator;
    this.collisionHelper = collisionHelper;
    this.eventBus = eventBus;
    this.dragZone = dragZone;
    this.resizeHelper = resizeHelper;
  }

  public void bindDisplay(final Display display) {
    this.display = display;
    displayWidgetResizeHandler = new EventsDashboardResizeHandler(display,events);

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
    resizeHelper.setDashboardDisplay(display);
  }

  private void clearEvents() {
    for (CalendarEvent event : events) {
      event.removeFromParent(display.asWidget());
      events.remove(event);
    }
  }


  
  public void addCalendarEvent(int index, Event event, int rowsCount) {


    int startRow = dateGenerator.getRowForInstant(event.getInterval().getStart().toInstant(), rowsCount);

    int endRow = dateGenerator.getRowForInstant(event.getInterval().getEnd().toInstant(), rowsCount);


    int[] startCellPosition = new int[]{startRow, index};
    int[] endCellPosition = new int[]{endRow, index};

            // TODO: will be refactored!
    int[] position = display.calculateLeftTop(startCellPosition);
    int height = display.getRowDistance(startRow, endRow);

    CalendarEvent calendarEvent = buildCalendarEvent(event, new EventPosition(position[0], position[1]), startCellPosition,endCellPosition);
    calendarEvent.setSize(display.getCellWidth(), height);
    events.add(calendarEvent);
    resizeHelper.attachResizeHelper(calendarEvent);

    calendarEvent.go(display.asWidget());
  }

  private CalendarEvent buildCalendarEvent(Event event, EventPosition eventPosition, int[] startCellPosition, int[] endCellPosition) {
    CalendarEvent calendarEvent = new CalendarEvent(event, eventPosition,startCellPosition,endCellPosition);
    CalendarEvent.Display display = this.display.getCalendarEventDisplay();
    calendarEvent.bindDisplay(display);
    dragZone.add(calendarEvent);
    return calendarEvent;
  }

  public boolean checkForCollision(int[] cell, int cellCount, int rowsCount, CalendarColumn column) {
    int[] end = new int[2];
    end[0] =  cell[0] + cellCount - 1;
    end[1] =  cell[1];
    Interval  interval = dateGenerator.getIntervalForRange(cell,end,rowsCount);
    return collisionHelper.checkEventsIntervals(events,interval,column);
  }
  
  public HandlerRegistration addEventResizeEndHandler(EventResizeEndHandler handler) {
    return display.getHasEventResizeEndHandlers().addEventResizeEndHandler(handler);
  }

  public HandlerRegistration addEventResizeStartHandler(EventResizeStartHandler handler) {
    return display.getHasEventResizeStartHandlers().addEventResizeEndHandler(handler);
  }

  public WidgetResizeHandler getEventsDachboardWidgetResizeHandler() {
    return displayWidgetResizeHandler;
  }
}
