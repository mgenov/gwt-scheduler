package gwtscheduler.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarDropHandler extends EventHandler{
  void onCalendarDrop(CalendarDropEvent event);
}
