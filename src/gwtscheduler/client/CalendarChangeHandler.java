package gwtscheduler.client;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarChangeHandler extends EventHandler{
  void onCalendarChange(CalendarChangeEvent event);
}
