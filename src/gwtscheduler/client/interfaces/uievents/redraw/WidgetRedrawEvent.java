package gwtscheduler.client.interfaces.uievents.redraw;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WidgetRedrawEvent extends GwtEvent<WidgetRedrawHandler> {

  /**
   * Event type for blur events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<WidgetRedrawHandler> TYPE = new Type<WidgetRedrawHandler>();

  /**
   * Gets the event type associated with resize events.
   * @return the handler type
   */
  public static Type<WidgetRedrawHandler> getType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WidgetRedrawHandler handler) {
    handler.onRedraw(this);
  }

  @Override
  public Type<WidgetRedrawHandler> getAssociatedType() {
    return TYPE;
  }
}
