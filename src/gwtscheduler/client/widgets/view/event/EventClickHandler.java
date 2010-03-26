package gwtscheduler.client.widgets.view.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface EventClickHandler extends EventHandler{
  void onEventClickEvent(EventClickEvent event);
}
