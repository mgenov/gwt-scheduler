package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.EventHandler;
import gwtscheduler.client.widgets.view.common.events.DropObjectEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DropObjectHandler extends EventHandler {
  void onDropObject(DropObjectEvent event);
}
