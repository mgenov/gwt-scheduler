package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DragOverlapHandler extends EventHandler{
  void onDragOverlap(DragOverlapEvent event);
}
