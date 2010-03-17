package dragndrop.client.core;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface DragInHandler extends EventHandler{
  void onDragInEvent(DragInEvent event);
}
