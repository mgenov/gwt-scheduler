package gwtscheduler.client.widgets.view.event;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.util.Interval;

/**
 * Fired when someone click on calendar event. Contains information about event and event interval.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class EventClickEvent extends GwtEvent<EventClickHandler>{
  public static Type<EventClickHandler> TYPE = new Type<EventClickHandler>();
  private final Event event;
  private final Interval interval;

  public EventClickEvent(Event event, Interval interval) {
    this.event = event;
    this.interval = interval;
  }

  @Override
  public Type<EventClickHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EventClickHandler handler) {
    handler.onEventClickEvent(this);
  }

  public Event getEvent() {
    return event;
  }

  public Interval getInterval() {
    return interval;
  }
}
