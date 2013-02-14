package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.common.util.Interval;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeEvent extends GwtEvent<CalendarEventResizeHandler> {
  public static Type<CalendarEventResizeHandler> TYPE = new Type<CalendarEventResizeHandler>();
  private final Interval currentInterval;
  private final CalendarEventResizeHelper calendarEventResizeHelper;
  private final CalendarEvent calendarEvent;

  public CalendarEventResizeEvent(Interval currentInterval, CalendarEventResizeHelper calendarEventResizeHelper, CalendarEvent calendarEvent) {
    this.currentInterval = currentInterval;
    this.calendarEventResizeHelper = calendarEventResizeHelper;
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<CalendarEventResizeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventResizeHandler handler) {
    handler.onCalendarEventResizeEvent(this);
  }

  public Interval getCurrentInterval() {
    return currentInterval;
  }

  public CalendarEventResizeHelper getCalendarEventResizeHelper() {
    return calendarEventResizeHelper;
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
