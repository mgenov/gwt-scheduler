package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventDurationIntervalUpdateEvent extends GwtEvent<CalendarEventDurationIntervaUpdateHandler>{
  public static Type<CalendarEventDurationIntervaUpdateHandler> TYPE = new Type<CalendarEventDurationIntervaUpdateHandler>();
  private final CalendarEvent calendarEvent;
  private final Instant startTime;
  private final Instant endTime;

  public CalendarEventDurationIntervalUpdateEvent(CalendarEvent calendarEvent, Instant startTime, Instant endTime) {
    this.calendarEvent = calendarEvent;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Type<CalendarEventDurationIntervaUpdateHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventDurationIntervaUpdateHandler handler) {
    handler.onCalendarEventDurationIntervalUpdate(this);
  }

  public Event getEvent() {
    return calendarEvent.getEvent();
  }

  public long getStartTime() {
    return startTime.getMillis();
  }

  public long getEndTime() {
    return endTime.getMillis();
  }
}
