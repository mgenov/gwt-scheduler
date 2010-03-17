package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface HasCalendarEventResizeStartHandlers extends HasHandlers {
  /**
   * Adds a {@link com.google.gwt.event.dom.client.ClickEvent} handler.
   *
   * @param handler the click handler
   * @return {@link com.google.gwt.event.shared.HandlerRegistration} used to remove this handler
   */
  HandlerRegistration addEventResizeEndHandler(CalendarEventResizeStartHandler handler);
}
