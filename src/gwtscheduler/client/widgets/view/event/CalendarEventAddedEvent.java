package gwtscheduler.client.widgets.view.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Indicates that new {@link gwtscheduler.client.widgets.view.event.CalendarEvent} has been added.
 *
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEventAddedEvent extends GwtEvent<CalendarEventAddedHandler> {

  public static final GwtEvent.Type<CalendarEventAddedHandler> TYPE = new GwtEvent.Type<CalendarEventAddedHandler>();
  private final CalendarEvent event;

  public CalendarEventAddedEvent(final CalendarEvent event) {
    this.event = event;
  }


  @Override
  protected void dispatch(CalendarEventAddedHandler handler) {
    handler.onCalendarEventAdded(this);
  }

  public CalendarEvent getCalendarEvent() {
    return event;
  }
  
  @Override
  public Type<CalendarEventAddedHandler> getAssociatedType() {
    return TYPE;
  }




}
