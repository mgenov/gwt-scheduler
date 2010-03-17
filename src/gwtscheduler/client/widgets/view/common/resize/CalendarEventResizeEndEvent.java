package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeEndEvent extends GwtEvent<CalendarEventResizeEndHandler>{
  public static Type<CalendarEventResizeEndHandler> TYPE = new Type<CalendarEventResizeEndHandler>();
  private final CalendarEvent calendarEvent;
  private final Instant startTime;
  private final Instant endTime;

  public CalendarEventResizeEndEvent(CalendarEvent calendarEvent, Instant startTime, Instant endTime) {
    this.calendarEvent = calendarEvent;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Type<CalendarEventResizeEndHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventResizeEndHandler handler) {
    handler.onCalendarEventResizeEndEvent(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }

  public Instant getStartTime() {
    return startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }
}
