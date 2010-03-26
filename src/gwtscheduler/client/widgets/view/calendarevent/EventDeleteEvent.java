package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import  gwtscheduler.client.widgets.view.event.Event;


/**
 * Fired when event is deleted.
 * 
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventDeleteEvent  extends GwtEvent<EventDeleteEventHandler>{
  public static final GwtEvent.Type<EventDeleteEventHandler> TYPE = new GwtEvent.Type<EventDeleteEventHandler>();

  private Event event;

  public EventDeleteEvent(Event event) {
    this.event = event;
  }

  @Override
  public Type<EventDeleteEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EventDeleteEventHandler handler) {
    handler.onEventDelete(this);
  }

  public Event getEvent() {
    return event;
  }
}
