package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.EventPosition;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventsDashboardResizeHandler implements WidgetResizeHandler {
  private EventsDashboard.Display target;
  private ArrayList<CalendarEvent> events;


  public EventsDashboardResizeHandler(EventsDashboard.Display target, ArrayList<CalendarEvent> events) {
    this.target = target;
    this.events = events;
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
  int width = event.width;

    if (width <= 0) {
      return;
    }

    AbsolutePanel parent = target.asWidget();
    
    for (CalendarEvent calendarEvent : events) {
      calendarEvent.setWidth(target.getCellWidth());
      int cellInterval = calendarEvent.getEndCellPosition()[0]-calendarEvent.getStartCellPosition()[0];
      calendarEvent.setHeight(cellInterval * target.getCellHeight());
      int[] startCell = calendarEvent.getStartCellPosition();
      int[] newPosition = target.calculateLeftTop(startCell);
      calendarEvent.setPosition(new EventPosition(newPosition[0],newPosition[1]));
      calendarEvent.removeFromParent(parent);
      calendarEvent.go(parent);
    }

  }
}
