package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import gwtscheduler.client.widgets.view.common.resize.EventResizeEndHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface HasEventResizeEndHandlers extends HasHandlers {
  /**
   * Adds a {@link com.google.gwt.event.dom.client.ClickEvent} handler.
   *
   * @param handler the click handler
   * @return {@link com.google.gwt.event.shared.HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addEventResizeEndHandler(EventResizeEndHandler handler);
}
