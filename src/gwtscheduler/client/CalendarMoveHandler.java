package gwtscheduler.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarMoveHandler extends EventHandler{
  void onCalendarMove(CalendarMoveEvent event);
}
