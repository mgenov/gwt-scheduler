package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CalendarChangeHandler extends EventHandler{
  void onCalendarChange(CalendarChangeEvent event);
}
