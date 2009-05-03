package gwtscheduler.client.interfaces.uievents.resize;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for resize-aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IWidgetResizeHandler extends EventHandler {

  /**
   * Fired when the parent widget was resized.
   * 
   * @param event the resize event
   */
  void onResize(WidgetResizeEvent event);
}
