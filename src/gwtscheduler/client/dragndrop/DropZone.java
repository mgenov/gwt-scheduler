package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Widgets that implements this interface will be drop zones and only this widgets will accept objects that is dropped.
 * After implementing this interface you can use this code to attach drop handlers to the widget.
 * <pre>
 * public HandlerRegistration addDropHandler(DropHandler handler){
 *   return addHandler(handler, DropEvent.TYPE);
 * }
 *
 * public void addDragOverHandler(DragOverHandler handler) {
 *   addHandler(handler, DragOverEvent.TYPE);
 * }
 *
 * public void addDragOutHandler(DragOutHandler handler) {
 *   addHandler(handler, DragOutEvent.TYPE);
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
  HandlerRegistration addDropHandler(DropHandler handler);

  /**
   * Attaches drag over handler. This handler accept event fired when dragged widget enter to the dragged zone.
   * @param handler drag over handler.
   */
  HandlerRegistration addDragInHandler(DragInHandler handler);

  /**
   * Attaches drag out handler. This handler accept event fired when dragged widget exit from dragged zone.
   * @param handler drag out handler.
   */
  HandlerRegistration addDragOutHandler(DragOutHandler handler);

  /**
   * Attaches drag over handler. This handler accept event fired when dragged widget is dragged over drop zone.
   * @param handler drag over handler.
   */
  HandlerRegistration addDragOverHandler(DragOverHandler handler);

}
