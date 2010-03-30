package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.EventHandler;
import gwtscheduler.client.widgets.view.common.events.CellDropEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CellDropHandler extends EventHandler {
  void onCellDrop(CellDropEvent event);
}
