package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
import dragndrop.client.core.*;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.events.DropObjectEvent;
import gwtscheduler.client.widgets.view.common.events.MoveObjectEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeHelperProvider;
import gwtscheduler.common.calendar.CalendarFrame;
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

    int getRowCount();
  }

  private static final String EVENT_IN_COLLISION = "The dropped event interval is in collision with other already exist event";
  private final EventBus eventBus;
  private final EventBus calendarBus;
  private final DateGenerator dateGenerator;
  private final CollisionDetector collisionDetector;
  private final CalendarEventResizeHelperProvider resizeHelper;
  private final DragZone dragZone;
  private WidgetResizeHandler displayWidgetResizeHandler;
  private Display display;
  private ArrayList<CalendarEvent> calendarEvents = new ArrayList<CalendarEvent>();
  private List<CalendarColumn> columns;
  private boolean collision = false;

  public EventsDashboard(DateGenerator dateGenerator, CollisionDetector collisionDetector, EventBus eventBus, EventBus calendarBus, CalendarEventResizeHelperProvider resizeHelper) {
    this.dateGenerator = dateGenerator;
    this.collisionDetector = collisionDetector;
    this.eventBus = eventBus;
    this.calendarBus = calendarBus;
    this.dragZone = Zones.getDragZone();

    Frame eventsFrame = Zones.getDragFrame();
    eventsFrame.setZIndex(33);
    eventsFrame.setCursorStyle(CursorStyle.POINTER.toString());
    dragZone.registerFrame(eventsFrame, CalendarEvent.class);
    this.resizeHelper = resizeHelper;
  }

  public void bindDisplay(final Display display) {
    this.display = display;
    this.dragZone.makeDragZone(display.asWidget());
    this.dragZone.addDropZone(display);
    displayWidgetResizeHandler = new EventsDashboardResizeHandler(this, calendarEvents);
    resizeHelper.setDashboardDisplay(display);


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
        clearEventsDashboard();
        renderCalendarEvents(calendarEvents);
      }
    });

    calendarBus.addHandler(CalendarEventResizeEvent.TYPE, new CalendarEventResizeHandler() {
      @Override
      public void onCalendarEventResizeEvent(CalendarEventResizeEvent event) {
        Interval currentInterval = event.getCurrentInterval();
        int columnIndex = event.getCalendarEvent().getStartCellPosition()[1];

        boolean isCollision = collisionDetector.isInCollision(calendarEvents, columnIndex, currentInterval, event.getCalendarEvent());

        if (isCollision) {
          event.getCalendarEventResizeHelper().setCursorStyle(CursorStyle.NOT_ALLOWED.toString());
          event.getCalendarEventResizeHelper().setInCollision(true);
        } else {
          event.getCalendarEventResizeHelper().setCursorStyle(CursorStyle.POINTER.toString());
          event.getCalendarEventResizeHelper().setInCollision(false);
        }
      }
    });

    display.addDragOverHandler(this);
    display.addDropHandler(this);
  }

  private void renderCalendarEvents(ArrayList<CalendarEvent> calendarEvents) {
    clearEventsDashboard();
    for (CalendarEvent calendarEvent : calendarEvents) {
        displayCaledarEvent(calendarEvent);
    }
  }

  public void clearEventsDashboard() {
    display.asWidget().clear();
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

    int cellCount = frame.getHeight() / cellHeight; // cells in frame
    int[] endCell = new int[] {cell[0] + cellCount - 1, cell[1]};
    
    // checks the end of the day
    if (endCell[0] > display.getRowCount() - 1) {
      endCell[0] = display.getRowCount() - 1;
    }
    Interval interval = dateGenerator.getIntervalForRange(cell, endCell, display.getRowCount());

    boolean isCollision = collisionDetector.isInCollision(calendarEvents, cell[1], interval, event.getDropObject());
    if (isCollision) {
      frame.setCursorStyle(CursorStyle.NOT_ALLOWED.toString());
      collision = true;
    } else {
      frame.setCursorStyle(CursorStyle.POINTER.toString());
      collision = false;
    }
  }

  public WidgetResizeHandler getEventsDachboardWidgetResizeHandler() {
    return displayWidgetResizeHandler;
  }

  public void setColumns(List<CalendarColumn> columns) {
    this.columns = columns;
  }



  public void addEvent(Event event) {
    CalendarEvent calendarEvent = buildCalendarEvent(event);
    calendarEvents.add(calendarEvent);
    displayCaledarEvent(calendarEvent);
  }
  

  public void displayCaledarEvent(CalendarEvent calendarEvent) {
    Interval currentInterval = dateGenerator.interval();
    GWT.log(currentInterval.getStart().toString() +" end "+currentInterval.getEnd().toString(),null);
    if(collisionDetector.isInCollision(calendarEvent.getInterval(),currentInterval)) {
    dragZone.add(calendarEvent);
    resizeHelper.attachResizeHelper(calendarEvent);
    calendarEvent.go(display.asWidget());
    }
  }

  
  // TODO: building on event can be completed by some object event builder!

  private CalendarEvent buildCalendarEvent(Event event) {
    int columnIndex = findColumnIndex(event);

    if (columnIndex < 0) {
      GWT.log("Not acceptable column found!", new IllegalStateException());
      return null; // or throw an exception!
    }

    Instant startTime = new Instant(event.getDurationInterval().getStart());
    Instant endTime = new Instant(event.getDurationInterval().getEnd()); 

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
    if (event != null){
    CalendarEvent calendarEvent = getEventById(event.getEventId());
    removeEvent(calendarEvents, calendarEvent);
    }
  }

  private ArrayList<Event> getEvents(ArrayList<CalendarEvent> calendarEvents) {
    ArrayList<Event> events = new ArrayList<Event>();
    for (CalendarEvent calendarEvent : calendarEvents) {
      events.add(calendarEvent.getEvent());
    }
    return events;
  }

  private void renderEvents(ArrayList<Event> events) {
    for (Event event : events) {
      addEvent(event);
    }
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
    for (CalendarEvent calendarEvent : calendarEvents) {
      if (id.equals(calendarEvent.getEvent().getEventId())) {
        return calendarEvent;
      }
    }
    return null;
  }

  private void removeEvent(ArrayList<CalendarEvent> events, CalendarEvent calendarEvent) {
    events.remove(calendarEvent);
    calendarEvent.removeFromParent(display.asWidget());
  }

  public void removeEventsForColumn(CalendarColumn calendarColumn) {
    ArrayList<CalendarEvent> columnEvents = new ArrayList<CalendarEvent>();
    for (int i = 0; i < calendarEvents.size(); i++) {
      if (calendarColumn.isEventForColumn(calendarEvents.get(i).getEvent())) {
        columnEvents.add(calendarEvents.get(i));
        calendarEvents.get(i).removeFromParent(display.asWidget());
      }
    }
    calendarEvents.removeAll(columnEvents);

    reRenderCalendarEvents(calendarEvents);
  }

  private void reRenderCalendarEvents(ArrayList<CalendarEvent> calendarEvents) {
    ArrayList<Event> events = getEvents(calendarEvents);
    calendarEvents.clear();
    clearEventsDashboard();
    renderEvents(events);
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
    Instant newTime = dateGenerator.getInstantForCell(newCell, display.getRowCount());

    if (calendarEvents.contains(event.getDroppedObject())) {
      int[] oldCell = display.getCellPosition(event.getStartX(), event.getStartY());

      Instant oldTime = dateGenerator.getInstantForCell(oldCell, display.getRowCount());

      MoveObjectEvent moveObject = new MoveObjectEvent(oldCell, newCell, oldTime, newTime, event.getDroppedObject());
      calendarBus.fireEvent(moveObject);
    } else {
      DropObjectEvent dropObject = new DropObjectEvent(newCell, newTime, event.getDroppedObject());
      calendarBus.fireEvent(dropObject);
    }
  }

  public Display getDisplay() {
    return display;
  }
}
