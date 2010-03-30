package dragndrop.client.core;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface DragOutHandler extends EventHandler {
  void onDragOutEvent(DragOutEvent event);
}
