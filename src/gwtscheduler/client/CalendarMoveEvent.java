package gwtscheduler.client;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarMoveEvent extends GwtEvent<CalendarMoveHandler>{
  public static final Type<CalendarMoveHandler> TYPE = new Type<CalendarMoveHandler>();

  @Override
  public Type<CalendarMoveHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarMoveHandler handler) {
    handler.onCalendarMove(this);
  }
}
