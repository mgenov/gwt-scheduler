package gwtscheduler.client.widgets.view.common.resize;

import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarEventResizeHelperProvider {
  CalendarEventResizeHelper get(CalendarEvent calendarEvent);

  void setDashboardDisplay(EventsDashboard.Display display);
}
