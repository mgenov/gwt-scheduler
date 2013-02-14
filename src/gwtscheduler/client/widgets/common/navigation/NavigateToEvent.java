package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.util.DateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class NavigateToEvent extends GwtEvent<NavigateToEventHandler>{
  public static final Type<NavigateToEventHandler> TYPE = new  GwtEvent.Type<NavigateToEventHandler>();
  private DateTime dateTime;

  public NavigateToEvent(DateTime dateTime) {
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
