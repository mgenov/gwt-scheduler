package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.GwtEvent;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class NavigateToEvent extends GwtEvent<NavigateToEventHandler>{
  public static final Type<NavigateToEventHandler> TYPE = new  GwtEvent.Type<NavigateToEventHandler>();
  private ReadableDateTime dateTime;

  public NavigateToEvent(ReadableDateTime dateTime) {
    this.dateTime = dateTime;
  }

  @Override
  public Type<NavigateToEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(NavigateToEventHandler handler) {
    handler.onNavigateTo(dateTime);
  }
}
