package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ResizeStartEvent extends GwtEvent<ResizeStartHandler> {
  public static Type<ResizeStartHandler> TYPE = new Type<ResizeStartHandler>();
  private final CalendarEvent calendarEvent;

  public ResizeStartEvent(CalendarEvent calendarEvent) {
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<ResizeStartHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ResizeStartHandler handler) {
    handler.onResizeStart(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
