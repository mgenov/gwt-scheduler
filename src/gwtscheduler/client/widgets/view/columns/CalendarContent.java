package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.dragndrop.DropZone;
import gwtscheduler.client.widgets.common.Cell;

import java.util.List;

/**
 * Represents the calendar content
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContent {
  public interface Display extends DropZone{
    CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay();

    void removeColumn(int calendarColumnIndex);

    void addColumn(String title);

    void fireResizeRedrawEvents();

    int[] getCell(int startX, int startY); // TODO: remove.. use EventDashboard presenter

    boolean isDashboardAttached(DropEvent event); // TODO: Refactor when EventDashboard finished and move logic there.

    void addTestTask(Widget display, int[] ints);
  }

  private CalendarColumnsFrameGrid calendarColumnsFrameGrid;
  private Display display;

  public CalendarContent(CalendarColumnsFrameGrid calendarColumnsFrameGrid) {
    this.calendarColumnsFrameGrid = calendarColumnsFrameGrid;
  }

  public void bindDisplay(Display display) {
    this.display = display;
    calendarColumnsFrameGrid.bindDisplay(display.getCalendarColumnsFrameGridDisplay());
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

  public interface ContentChange {
    void onDrop(int[] newCell, Object droppedObject);

    void onMove(int[] oldCell, int[] newCell, Object droppedObject);
  }

  public void addTestTask(Widget display) {
    this.display.addTestTask(display, new int[]{2,2});
  }
}
