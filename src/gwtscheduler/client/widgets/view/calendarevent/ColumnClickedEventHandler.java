package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public interface ColumnClickedEventHandler extends EventHandler {
  void onColumnTitleClicked(ColumnClickedEvent event);
}
