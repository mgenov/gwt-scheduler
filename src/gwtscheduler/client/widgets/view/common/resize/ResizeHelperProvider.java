package gwtscheduler.client.widgets.view.common.resize;

import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ResizeHelperProvider implements ResizeHelper{
  private EventsDashboard.Display display;
  private final DateGenerator dateGenerator;

  public ResizeHelperProvider(DateGenerator dateGenerator) {
    this.dateGenerator = dateGenerator;
  }

  @Override
  public void setDashboardDisplay(EventsDashboard.Display display) {
    this.display = display;
  }

  public CalendarEventResizeHelper get(CalendarEvent calendarEvent) {
    CalendarEventResizeHelper helper = new CalendarEventResizeHelper(calendarEvent, display, dateGenerator, new CalendarEventResizeHelperView());
    return helper;
  }
}
