package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.EventHandler;
import gwtscheduler.client.widgets.view.common.events.MoveObjectEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface MoveObjectHandler extends EventHandler{
  void onMoveObject(MoveObjectEvent event);
}
