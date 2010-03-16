package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ResizeEndEvent extends GwtEvent<ResizeEndHandler>{
  public static Type<ResizeEndHandler> TYPE = new Type<ResizeEndHandler>();
  private final CalendarEvent calendarEvent;

  public ResizeEndEvent(CalendarEvent calendarEvent) {
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<ResizeEndHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ResizeEndHandler handler) {
    handler.onResizeEnd(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
