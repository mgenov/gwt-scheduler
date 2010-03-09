package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContent {
  public interface Display {
    CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay();

    void removeColumnHeader(int calendarColumnIndex);

    void addColumn(String title);

    int[] getCellAt(int endX, int endY);

    void attachWidget(Widget sourceWidget, int[] cell);
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
    display.removeColumnHeader(index);
  }

  public void addColumn(String title) {
    display.addColumn(title);
  }

  public WidgetResizeHandler getWidgetResizeHandler() {
    return display.getCalendarColumnsFrameGridDisplay().getWidgetResizeHandler();
  }

  public int[] getCellPosition(int endX, int endY) {
    return display.getCellAt(endX, endY);
  }

  public void attachWidget(Widget sourceWidget, int[] cell) {
    display.attachWidget(sourceWidget, cell);
  }
}
