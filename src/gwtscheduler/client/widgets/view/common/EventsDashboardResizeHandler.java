package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.client.widgets.view.event.EventPosition;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventsDashboardResizeHandler implements WidgetResizeHandler {
  private EventsDashboard target;
  private ArrayList<CalendarEvent> events;


  public EventsDashboardResizeHandler(EventsDashboard target, ArrayList<CalendarEvent> events) {
    this.target = target;
    this.events = events;
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
  int width = event.width;

    if (width <= 0) {
      return;
    }

    EventsDashboard.Display targetDisplay =  target.getDisplay();
    target.clearEventsDashboard();
    
    for (CalendarEvent calendarEvent : events) {
      calendarEvent.setWidth(targetDisplay.getCellWidth());
      int cellInterval = calendarEvent.getEndCellPosition()[0]-calendarEvent.getStartCellPosition()[0];
      calendarEvent.setHeight(cellInterval * targetDisplay.getCellHeight());
      int[] startCell = calendarEvent.getStartCellPosition();
      int[] newPosition = targetDisplay.calculateLeftTop(startCell);
      calendarEvent.setPosition(new EventPosition(newPosition[0],newPosition[1]));
      target.displayCaledarEvent(calendarEvent);
    }
  }
}
