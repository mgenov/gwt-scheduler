package gwtscheduler.common.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Fire this event when some event is deleted. Contains deleted event.
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarEventDeleteEvent extends GwtEvent<CalendarEventDeleteEventHandler> {
  public static final GwtEvent.Type<CalendarEventDeleteEventHandler> TYPE = new GwtEvent.Type<CalendarEventDeleteEventHandler>();
  private Event event;

  public CalendarEventDeleteEvent(Event event) {
    this.event = event;
  }

  @Override
  public GwtEvent.Type<CalendarEventDeleteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarEventDeleteEventHandler handler) {
    handler.onEventDelete(this);
  }

  public Event getEvent() {
    return event;
  }
}
