package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class NavigatePreviousEvent extends GwtEvent<NavigatePreviousEventHandler>{
  public static final Type<NavigatePreviousEventHandler> TYPE = new  GwtEvent.Type<NavigatePreviousEventHandler>();

  @Override
  public Type<NavigatePreviousEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(NavigatePreviousEventHandler handler) {
    handler.onNavigatePrevious();
  }
}
