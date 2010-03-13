package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.common.Cell;

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

  }

  private CalendarColumnsFrameGrid calendarColumnsFrameGrid;
  private Display display;

  public CalendarContent(CalendarColumnsFrameGrid calendarColumnsFrameGrid) {
    this.calendarColumnsFrameGrid = calendarColumnsFrameGrid;
  }

  public void bindDisplay(Display display) {
    this.display = display;
    calendarColumnsFrameGrid.bindDisplay(display.getCalendarColumnsFrameGridDisplay());
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
    int frameWidth = calendarColumnsFrameGrid.getCellWidth();
    int frameHeight =  calendarColumnsFrameGrid.getCellHeight() /*   * event.getDuration()   */ * 2;

    frame.setWidth(frameWidth);
    frame.setHeight(frameHeight);
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

}
