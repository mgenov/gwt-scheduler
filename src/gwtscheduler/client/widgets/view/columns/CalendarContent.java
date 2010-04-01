package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.Element;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.event.Event;

import java.util.List;

/**
 * Represents the calendar content
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarContent {
  public interface Display extends DropZone{
    
    CalendarColumnsFrameGrid.Display getCalendarColumnsFrameGridDisplay();

    void removeColumn(int calendarColumnIndex);

    void addColumn(String title);

    void fireResizeRedrawEvents();

    EventsDashboard.Display getEventsDashboard();

    void setEnable(boolean enable);

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

    display.addDragOverHandler(eventsDashboard);
    display.addDropHandler(eventsDashboard);
  }

  public List<Cell<Element>> getFrameGridDecorables() {
    return calendarColumnsFrameGrid.getTimeLineDecorables();
  }

  public void removeColumn(CalendarColumn calendarColumn, int index) {
    eventsDashboard.removeEventsForColumn(calendarColumn);
    display.removeColumn(index);
  }

  public void addColumn(String title) {
    display.addColumn(title);
  }

  public void fireResizeRedrawEvents() {
    display.fireResizeRedrawEvents();
  }

  public void addCalendarEvent(Event event) {
    eventsDashboard.addEvent(event);
  }

  public void setColumns(List<CalendarColumn> columns) {
    eventsDashboard.setColumns(columns);
  }

  public WidgetResizeHandler getEventsDachboardWidgetResizeHandler() {
    return eventsDashboard.getEventsDachboardWidgetResizeHandler();
  }

  public void updateEvent(Event event) {
    eventsDashboard.updateEvent(event);
  }

  public void deleteEvent(Event event) {
    eventsDashboard.deleteEvent(event);
  }

  public void setEnable(boolean enable) {
    display.setEnable(enable);
  }
}
