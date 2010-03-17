package gwtscheduler.client.widgets.view.common.resize;

import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarEventResizeHelperProvider {
  CalendarEventResizeHelper attachResizeHelper(CalendarEvent calendarEvent);

  void setDashboardDisplay(EventsDashboard.Display display);
}
