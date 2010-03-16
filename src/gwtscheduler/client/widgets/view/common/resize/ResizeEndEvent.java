package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class ResizeEndEvent extends GwtEvent<ResizeEndHandler>{
  public static Type<ResizeEndHandler> TYPE = new Type<ResizeEndHandler>();
  private final CalendarEvent calendarEvent;
  private final Instant startTime;
  private final Instant endTime;

  public ResizeEndEvent(CalendarEvent calendarEvent, Instant startTime, Instant endTime) {
    this.calendarEvent = calendarEvent;
    this.startTime = startTime;
    this.endTime = endTime;
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

  public Instant getStartTime() {
    return startTime;
  }

  public Instant getEndTime() {
    return endTime;
  }
}
