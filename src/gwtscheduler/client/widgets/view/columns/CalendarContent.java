package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import dragndrop.client.core.*;
import gwtscheduler.common.event.Event;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.calendar.CalendarFrame;

import java.util.List;

/**
 * Represents the calendar content
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
  private EventsDashboard eventsDashboard;
  private Display display;

  public CalendarContent(CalendarColumnsFrameGrid calendarColumnsFrameGrid, EventsDashboard eventsDashboard) {
    this.calendarColumnsFrameGrid = calendarColumnsFrameGrid;
    this.eventsDashboard = eventsDashboard;
  }

  public void bindDisplay(Display display) {
    this.display = display;

    calendarColumnsFrameGrid.bindDisplay(display.getCalendarColumnsFrameGridDisplay());
    eventsDashboard.bindDisplay(display.getEventsDashboard());

    display.addDragOverHandler(new DragOverHandler(){
      @Override
      public void onDragOver(DragOverEvent event) {
        proceedDragOver(event);
      }
    });
  }

  private void proceedDragOver(DragOverEvent event){
    int[] cell = display.getCell(event.getMouseX(), event.getMouseY());
    int[] windowCellPosition = display.getWindowCellPosition(cell);

    DragZone hasFrame = event.getDragZone();

    hasFrame.setFrameWindowPosition(windowCellPosition[0], windowCellPosition[1]);

    Frame frame = hasFrame.getCurrentFrame();
    if(frame instanceof CalendarFrame){
      int cellWidth = calendarColumnsFrameGrid.getCellWidth();
      int cellHeight =  calendarColumnsFrameGrid.getCellHeight();

      CalendarFrame cellFrame = (CalendarFrame)frame;
      cellFrame.onDragOver(cellWidth, cellHeight);
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

  public void addContentChangeCallback(final ContentChange contentChange){
    display.addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        int[] newCell = display.getCell(event.getEndX(), event.getEndY());

        if(display.isDashboardAttached(event)){
          int[] oldCell = display.getCell(event.getStartX(), event.getStartY());
          contentChange.onMove(oldCell, newCell, event.getDroppedObject());
        } else {
          contentChange.onDrop(newCell, event.getDroppedObject());
        }
      }
    });
  }

  public void addCalendarEvent(List<CalendarColumn> columns, Event event) {
    int index=0;
    for (CalendarColumn column : columns) {
      if(column.isEventForColumn(event)){
        eventsDashboard.addCalendarEvent(index,event);
      }
      index++;
    }
  }
}
