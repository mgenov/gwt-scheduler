package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import dragndrop.client.core.*;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEventHandler;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.*;
import gwtscheduler.common.calendar.CalendarFrame;
import gwtscheduler.common.calendar.EventsFrame;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventPosition;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class EventsDashboard implements DropHandler, DragOverHandler {

  public interface Display extends DropZone {

    AbsolutePanel asWidget();

    int[] getCellPosition(int x, int y);

    int[] getWindowCellPosition(int[] cell);

    int[] calculateLeftTop(int[] cellPos);

    CalendarEvent.Display getCalendarEventDisplay();

    int getCellWidth();

    int getCellHeight();

    int getRowDistance(int start, int end);

    HasCalendarEventResizeEndHandlers getHasCalendarEventResizeEndHandlers();

    HasCalendarEventResizeStartHandlers getHasCalendarEventResizeStartHandlers();

    HasCalendarEventResizeHandlers getHasCalendarEventResizeHandlers();

    int getRowCount();
  }

  private static final String EVENT_IN_COLLISION = "The dropped event interval is in collision with other already exist event";
  private final EventBus eventBus;
  private final EventBus calendarBus;
  private final DateGenerator dateGenerator;
  private final EventCollisionHelper collisionHelper;
  private final CalendarEventResizeHelperProvider resizeHelper;
  private final DragZone dragZone;
  private WidgetResizeHandler displayWidgetResizeHandler;
  private Display display;
  private ArrayList<CalendarEvent> events = new ArrayList<CalendarEvent>();
  private List<CalendarColumn> columns;
  private boolean collision = false;

  public EventsDashboard(DateGenerator dateGenerator, EventCollisionHelper collisionHelper, EventBus eventBus, EventBus calendarBus, CalendarEventResizeHelperProvider resizeHelper) {
    this.dateGenerator = dateGenerator;
    this.collisionHelper = collisionHelper;
    this.eventBus = eventBus;
    this.calendarBus = calendarBus;
    this.dragZone = Zones.getDragZone();

    EventsFrame eventsFrame = new EventsFrame(Zones.getFrameDisplay());
    dragZone.registerFrame(eventsFrame, CalendarEvent.class);
    this.resizeHelper = resizeHelper;
  }

  public void bindDisplay(final Display display) {
    this.display = display;
    this.dragZone.makeDragZone(display.asWidget());
    this.dragZone.addDropZone(display);
    displayWidgetResizeHandler = new EventsDashboardResizeHandler(display, events);

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

    display.getHasCalendarEventResizeHandlers().addEventResizeEndHandler(new CalendarEventResizeHandler() {
      @Override
      public void onCalendarEventResizeEvent(CalendarEventResizeEvent event) {

        Interval currentInterval = event.getCurrentInterval();
        if (collisionHelper.checkEventsIntervals(events, currentInterval, event.getCalendarEvent())) {
          event.getCalendarEventResizeHelper().setCursorStyle(CursorStyle.NOT_ALLOWED.toString());
        } else {
          event.getCalendarEventResizeHelper().setCursorStyle(CursorStyle.POINTER.toString());
        }
      }
    });

    display.addDragOverHandler(this);
    display.addDropHandler(this);
  }

  private void clearEvents() {
    display.asWidget().clear();
    events.clear();
  }

  private void proceedDragOver(DragOverEvent event) {
    int[] cell = display.getCellPosition(event.getMouseX(), event.getMouseY());
    int[] windowCellPosition = display.getWindowCellPosition(cell);

    DragZone dragZone = event.getDragZone();

    dragZone.setFrameWindowPosition(windowCellPosition[0], windowCellPosition[1]);

    int cellWidth = display.getCellWidth();
    int cellHeight = display.getCellHeight();

    Frame frame = dragZone.getCurrentFrame();

    if (frame instanceof CalendarFrame) {
      CalendarFrame cellFrame = (CalendarFrame) frame;
      cellFrame.onDragOver(cellWidth, cellHeight);
    }

    int cellCount = frame.getHeight() / cellHeight;
    CalendarColumn column = columns.get(cell[1]);
    if (checkForCollision(cell, cellCount, display.getRowCount(), column, event.getDropObject())) {
      frame.setCursorStyle(CursorStyle.NOT_ALLOWED.toString());
      collision = true;
    } else {
      frame.setCursorStyle(CursorStyle.POINTER.toString());
      collision = false;
    }
  }

  private boolean checkForCollision(int[] cell, int cellCount, int rowsCount, CalendarColumn column, Object dropObject) {
    int[] end = new int[2];
    end[0] = cell[0] + cellCount - 1;
    end[1] = cell[1];

    // checks the end of the day
    if (end[0] > rowsCount - 1) {
      return true;
    }
    Interval interval = dateGenerator.getIntervalForRange(cell, end, rowsCount);
    return collisionHelper.checkEventsIntervals(events, interval, column, dropObject);
  }

  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
    return display.getHasCalendarEventResizeEndHandlers().addEventResizeEndHandler(handler);
  }

  public HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    return display.getHasCalendarEventResizeStartHandlers().addEventResizeEndHandler(handler);
  }

  public WidgetResizeHandler getEventsDachboardWidgetResizeHandler() {
    return displayWidgetResizeHandler;
  }

  public void setColumns(List<CalendarColumn> columns) {
    this.columns = columns;
  }

  public void addEvent(Event event) {
    CalendarEvent calendarEvent = buildCalendarEvent(event);
    events.add(calendarEvent);
    dragZone.add(calendarEvent);
    resizeHelper.attachResizeHelper(calendarEvent);
    calendarEvent.go(display.asWidget());
  }
  // TODO: building on event can be completed by some object event builder!

  private CalendarEvent buildCalendarEvent(Event event) {
    int columnIndex = findColumnIndex(event);

    if (columnIndex < 0) {
      GWT.log("Not acceptable column found!", new IllegalStateException());
      return null; // or throw an exception!
    }

    Instant startTime = event.getInterval().getStart().toInstant();
    Instant endTime = event.getInterval().getEnd().toInstant();

    int rowsCount = display.getRowCount();
    int startRow = dateGenerator.getRowForInstant(startTime, rowsCount);
    int endRow = dateGenerator.getRowForInstant(endTime, rowsCount);

    // checks if end row is at 00.00 on the next day
    if (endRow < startRow && endRow == 0) {
      endRow = rowsCount;
    }

    int[] startCellPosition = new int[]{startRow, columnIndex};
    int[] endCellPosition = new int[]{endRow, columnIndex};

    // TODO: will be refactored!
    int[] position = display.calculateLeftTop(startCellPosition);
    int height = display.getRowDistance(startRow, endRow);

    CalendarEvent calendarEvent = new CalendarEvent(event, new EventPosition(position[0], position[1]), startCellPosition, endCellPosition, eventBus);
    CalendarEvent.Display display = this.display.getCalendarEventDisplay();
    calendarEvent.bindDisplay(display);

    calendarEvent.setSize(this.display.getCellWidth(), height);
    return calendarEvent;
  }

  public void updateEvent(Event event) {
    CalendarEvent calendarEvent = getEventById(event.getEventId());
    deleteEvent(calendarEvent.getEvent());
    addEvent(event);
  }

  public void deleteEvent(Event event) {
    CalendarEvent calendarEvent = getEventById(event.getEventId());
    events.remove(calendarEvent);
    calendarEvent.removeFromParent(display.asWidget());
  }

  private int findColumnIndex(Event event) {
    for (int i = 0; i < columns.size(); i++) {
      if (columns.get(i).isEventForColumn(event)) {
        return i;
      }
    }

    return -1;
  }

  public CalendarEvent getEventById(String id) {
    for (CalendarEvent calendarEvent : events) {
      if (id.equals(calendarEvent.getEvent().getEventId())) {
        return calendarEvent;
      }
    }

    return null;
  }

  @Override
  public void onDragOver(DragOverEvent event) {
    proceedDragOver(event);
  }

  @Override
  public void onDrop(DropEvent event) {
    if (collision) {
      throw new EventIntervalCollisionException(EVENT_IN_COLLISION);
    }

    int[] newCell = display.getCellPosition(event.getEndX(), event.getEndY());

    if (events.contains(event.getDroppedObject())) {
      int[] oldCell = display.getCellPosition(event.getStartX(), event.getStartY());
      MoveObjectEvent moveObject = new MoveObjectEvent(oldCell, newCell, event.getDroppedObject());
      calendarBus.fireEvent(moveObject);
    } else {
      DropObjectEvent dropObject = new DropObjectEvent(newCell, event.getDroppedObject());
      calendarBus.fireEvent(dropObject);
    }
  }
}
