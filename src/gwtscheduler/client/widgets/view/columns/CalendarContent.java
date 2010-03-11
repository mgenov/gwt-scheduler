package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.CalendarDropEvent;
import gwtscheduler.client.CalendarDropHandler;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.dragndrop.DropZone;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

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

  public void addCalendarDropHandler(final CalendarDropHandler calendarDropHandler) {
    display.addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        calendarDropHandler.onCalendarDrop(new CalendarDropEvent());
      }
    });
  }
}
