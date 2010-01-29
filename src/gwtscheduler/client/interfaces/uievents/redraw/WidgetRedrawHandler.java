package gwtscheduler.client.interfaces.uievents.redraw;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface WidgetRedrawHandler extends EventHandler {

  /**
   * Fired when a redraw is needed
   * @param event the redraw event
   */
  void onRedraw(WidgetRedrawEvent widgetRedrawEvent);
}
