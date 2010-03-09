package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarDropEvent extends GwtEvent<CalendarDropHandler>{
  public static final Type<CalendarDropHandler> TYPE = new Type<CalendarDropHandler>();
  
  @Override
  public Type<CalendarDropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarDropHandler handler) {
    handler.onCalendarDrop(this);
  }
}
