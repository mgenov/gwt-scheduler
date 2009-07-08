package gwtscheduler.client.interfaces.uievents.resize;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface HasResizeHandler {

  /**
   * Gets the resize handler for this instance.
   * @return the resize handler
   */
  WidgetResizeHandler getResizeHandler();
}