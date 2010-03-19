package gwtscheduler.client.widgets.view.common.resize;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperProviderImpl implements CalendarEventResizeHelperProvider {
  private final DateGenerator dateGenerator;
  private EventBus calendarBus;
  private EventsDashboard.Display display;

  public CalendarEventResizeHelperProviderImpl(DateGenerator dateGenerator, EventBus calendarBus) {
    this.dateGenerator = dateGenerator;
    this.calendarBus = calendarBus;
  }

  @Override
  public void setDashboardDisplay(EventsDashboard.Display display) {
    this.display = display;
  }

  public CalendarEventResizeHelper attachResizeHelper(CalendarEvent calendarEvent) {
    CalendarEventResizeHelperImpl helper = new CalendarEventResizeHelperImpl(calendarEvent, display, dateGenerator, new CalendarEventResizeHelperView(), calendarBus);
    return helper;
  }
}
