package gwtscheduler.client.widgets.common.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface WidgetResizeHandler extends EventHandler {

  /**
   * Fired when the parent widget was resized.
   * @param event the resize event
   */
  void onResize(WidgetResizeEvent event);
}
