package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Element;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEndHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.Event;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.calendar.CalendarFrame;

import java.util.List;

/**
 * Represents the calendar content
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContent {
  public interface Display extends DropZone {
    CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay();

    void removeColumn(int calendarColumnIndex);

    void addColumn(String title);

    void fireResizeRedrawEvents();

    int[] getCell(int startX, int startY); // TODO: remove.. use EventDashboard presenter

    boolean isDashboardAttached(DropEvent event); // TODO: Refactor when EventDashboard finished and move logic there.

    int[] getWindowCellPosition(int[] cell); // TODO: remove.. use EventDashboard presenter

    EventsDashboard.Display getEventsDashboard();
  }

  private CalendarColumnsFrameGrid calendarColumnsFrameGrid;
  private boolean collision = false;
  private EventsDashboard eventsDashboard;
  private Display display;
  private List<CalendarColumn> columns;
  private static final String NOT_ALLOWED = "not-allowed";
  private static final String DEFAULT = "default";

  public CalendarContent(CalendarColumnsFrameGrid calendarColumnsFrameGrid, EventsDashboard eventsDashboard) {
    this.calendarColumnsFrameGrid = calendarColumnsFrameGrid;
    this.eventsDashboard = eventsDashboard;
  }

  public void bindDisplay(Display display) {
    this.display = display;

    calendarColumnsFrameGrid.bindDisplay(display.getCalendarColumnsFrameGridDisplay());
    eventsDashboard.bindDisplay(display.getEventsDashboard());

    display.addDragOverHandler(new DragOverHandler() {
      @Override
      public void onDragOver(DragOverEvent event) {
        proceedDragOver(event);
      }
    });
  }

  private void proceedDragOver(DragOverEvent event) {
    int[] cell = display.getCell(event.getMouseX(), event.getMouseY());
    int[] windowCellPosition = display.getWindowCellPosition(cell);

    DragZone hasFrame = event.getDragZone();


    hasFrame.setFrameWindowPosition(windowCellPosition[0], windowCellPosition[1]);

    Frame frame = hasFrame.getCurrentFrame();


    if (frame instanceof CalendarFrame) {
      int cellWidth = calendarColumnsFrameGrid.getCellWidth();
      int cellHeight = calendarColumnsFrameGrid.getCellHeight();

      CalendarFrame cellFrame = (CalendarFrame) frame;
      cellFrame.onDragOver(cellWidth, cellHeight);

      int cellCount = frame.getHeight() / cellHeight;
      CalendarColumn column = columns.get(cell[1]);
      if (eventsDashboard.checkForCollision(cell, cellCount, calendarColumnsFrameGrid.getTimeLineDecorables().size(), column)) {
        frame.setCursorStyle(CursorStyle.NOT_ALLOWED.toString());
        collision = true;
      } else {
        frame.setCursorStyle(CursorStyle.POINTER.toString());
        collision = false;
      }
    }
  }

  public List<Cell<Element>> getFrameGridDecorables() {
    return calendarColumnsFrameGrid.getTimeLineDecorables();
  }

  public void removeColumn(int index) {
    display.removeColumn(index);
  }

  public void addColumn(String title) {
    display.addColumn(title);
  }

  public void fireResizeRedrawEvents() {
    display.fireResizeRedrawEvents();
  }

  public void addContentChangeCallback(final ContentChange contentChange) {
    display.addDropHandler(new DropHandler() {
      @Override
      public void onDrop(DropEvent event) {
        if (collision) {
          throw new RuntimeException("events collision");
        }
        int[] newCell = display.getCell(event.getEndX(), event.getEndY());

        if (display.isDashboardAttached(event)) {
          int[] oldCell = display.getCell(event.getStartX(), event.getStartY());
          contentChange.onMove(oldCell, newCell, event.getDroppedObject());
        } else {
          contentChange.onDrop(newCell, event.getDroppedObject());
        }
      }
    });
  }

  public void addCalendarEvent(List<CalendarColumn> columns, Event event) {
    int rowsCount = display.getCalendarColumnsFrameGridDisplay().getRows();
    int index = 0;
    for (CalendarColumn column : columns) {
      if (column.isEventForColumn(event)) {
        eventsDashboard.addCalendarEvent(index, event, rowsCount);
      }
      index++;
    }
  }

  public void setColumns(List<CalendarColumn> columns) {
    this.columns = columns;
  }
  
  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
    return eventsDashboard.addEventResizeEndHandler(handler);
  }

  public HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    return eventsDashboard.addEventResizeStartHandler(handler);
  }
}
