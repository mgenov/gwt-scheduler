package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface DropHandler extends EventHandler {
  void onDrop(DropEvent event);
}
