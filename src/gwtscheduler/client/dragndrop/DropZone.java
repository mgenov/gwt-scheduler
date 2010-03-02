package gwtscheduler.client.dragndrop;

/**
 * Widgets that implements this interface will be drop zones and only this widgets will accept objects that is dropped.
 * After implementing this interface you can use this code to attach drop handlers to the widget.
 * <pre>
 * public void addDropHandler(DropHandler handler){
 *   addHandler(handler, DropEvent.TYPE);
 * }
 * </pre>
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface DropZone {

  /**
   * Attaches drop handler that will handle dropped object.
   * @param handler drop handler.
   */
  void addDropHandler(DropHandler handler);

  /**
   * Attaches drag overlap handler. This handler accept event fired when dragged widget enter to the dragged zone.
   * @param handler drag overlap handler.
   */
  void addDragOverlapHandler(DragOverlapHandler handler);

}
