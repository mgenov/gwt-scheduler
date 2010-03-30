package gwtscheduler.client.widgets.common.event;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface HasWidgetResizeHandlers {

  /**
   * Adds a {@link WidgetResizeEvent} handler.
   * @param handler the focus handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler);
}
