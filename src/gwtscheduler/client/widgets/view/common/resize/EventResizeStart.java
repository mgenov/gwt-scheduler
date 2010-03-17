package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class EventResizeStart extends GwtEvent<EventResizeStartHandler> {
  public static Type<EventResizeStartHandler> TYPE = new Type<EventResizeStartHandler>();
  private final CalendarEvent calendarEvent;

  public EventResizeStart(CalendarEvent calendarEvent) {
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<EventResizeStartHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EventResizeStartHandler handler) {
    handler.onResizeStart(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
