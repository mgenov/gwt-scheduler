package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeStartEvent extends GwtEvent<CalendarEventResizeStartHandler> {
  public static Type<CalendarEventResizeStartHandler> TYPE = new Type<CalendarEventResizeStartHandler>();
  private final CalendarEvent calendarEvent;

  public CalendarEventResizeStartEvent(CalendarEvent calendarEvent) {
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<CalendarEventResizeStartHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventResizeStartHandler handler) {
    handler.onCalendarEventResizeStartEvent(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
