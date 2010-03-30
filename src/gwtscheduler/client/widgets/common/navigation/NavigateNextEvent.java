package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class NavigateNextEvent extends GwtEvent<NavigateNextEventHandler>{
  public static final Type<NavigateNextEventHandler> TYPE = new GwtEvent.Type<NavigateNextEventHandler>();

  @Override
  public Type<NavigateNextEventHandler> getAssociatedType() {
    return TYPE; 
  }

  @Override
  protected void dispatch(NavigateNextEventHandler handler) {
    handler.onNavigateNext();
  }
}
