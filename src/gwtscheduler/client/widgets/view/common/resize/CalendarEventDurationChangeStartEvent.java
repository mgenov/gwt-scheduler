package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.event.CalendarEvent;

/**
 * Fired when someone click to resize calendar event. Contain Information about who event will be resized.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventDurationChangeStartEvent extends GwtEvent<CalendarEventDurationChangeStartHandler> {
  public static Type<CalendarEventDurationChangeStartHandler> TYPE = new Type<CalendarEventDurationChangeStartHandler>();
  private final CalendarEvent calendarEvent;

  public CalendarEventDurationChangeStartEvent(CalendarEvent calendarEvent) {
    this.calendarEvent = calendarEvent;
  }

  @Override
  public Type<CalendarEventDurationChangeStartHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventDurationChangeStartHandler handler) {
    handler.onCalendarEventDurationChangeStart(this);
  }

  public CalendarEvent getCalendarEvent() {
    return calendarEvent;
  }
}
