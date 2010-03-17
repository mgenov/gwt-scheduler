package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Interval;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeEvent extends GwtEvent<CalendarEventResizeHandler> {
  public static Type<CalendarEventResizeHandler> TYPE = new Type<CalendarEventResizeHandler>();
  private final Interval currentInterval;
  private final CalendarEventResizeHelper calendarEventResizeHelper;
  private final int column;

  public CalendarEventResizeEvent(Interval currentInterval, CalendarEventResizeHelper calendarEventResizeHelper, int column) {
    this.currentInterval = currentInterval;
    this.calendarEventResizeHelper = calendarEventResizeHelper;
    this.column = column;
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

  public int getColumn() {
    return column;
  }
}
