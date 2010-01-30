package gwtscheduler.client.interfaces.uievents.lasso;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface HasLassoHandlers {

  /**
   * Adds a {@link LassoEventHandler} handler.
   * @param handler the lasso handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addLassoHandler(LassoEventHandler handler);
}
