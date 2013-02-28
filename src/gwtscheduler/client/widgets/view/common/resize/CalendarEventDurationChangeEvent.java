package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.common.util.DateTime;

/**
 * Fired when calendar event height size is changed. Contain information about event who size is changed, Event start
 * time and event end time.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventDurationChangeEvent extends GwtEvent<CalendarEventDurationChangeHandler>{
  public static Type<CalendarEventDurationChangeHandler> TYPE = new Type<CalendarEventDurationChangeHandler>();
  private final CalendarEvent calendarEvent;
  private final DateTime startTime;
  private final DateTime endTime;

  public CalendarEventDurationChangeEvent(CalendarEvent calendarEvent, DateTime startTime, DateTime endTime) {
    this.calendarEvent = calendarEvent;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public Type<CalendarEventDurationChangeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventDurationChangeHandler handler) {
    handler.onCalendarEventDurationChange(this);
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
