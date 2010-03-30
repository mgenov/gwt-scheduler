package gwtscheduler.client.widgets.common.event;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface HasWidgetRedrawHandlers {

  /**
   * Adds a {@link WidgetRedrawEvent} handler.
   * @param handler the redraw handler
   * @return {@link HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler);
}
