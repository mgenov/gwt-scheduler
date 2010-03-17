package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeEvent extends GwtEvent<CalendarEventResizeHandler> {
  public static Type<CalendarEventResizeHandler> TYPE = new Type<CalendarEventResizeHandler>();
  
  @Override
  public Type<CalendarEventResizeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventResizeHandler handler) {
    handler.onCalendarEventResizeEvent(this);
  }
}
