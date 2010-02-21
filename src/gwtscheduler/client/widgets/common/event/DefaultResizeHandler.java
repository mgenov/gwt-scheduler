package gwtscheduler.client.widgets.common.event;


import com.google.gwt.user.client.ui.Widget;

/**
 * Handlers resize events for the wrapper widget.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DefaultResizeHandler extends AbstractResizeHandler<Widget> implements WidgetResizeHandler {

  /**
   * Creates a new resize handler for the supplied widget.
   * @param widget the widget thath should handle resizes
   */
  public DefaultResizeHandler(Widget widget) {
    super(widget);
  }

  /**
   * Called when a viewport resize event ocurred.
   * @param event the resize event
   */
  @Override
  public void onResize(WidgetResizeEvent event) {
    super.onResize(event);
    getTarget().setSize(event.width + "px", event.height + "px");
    //    getTarget().setSize(event.width + "px", "100%");
  }

  @Override
  protected void onDelayedResize(WidgetResizeEvent event) {
    onResize(event);
  }

}
