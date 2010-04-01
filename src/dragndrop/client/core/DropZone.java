package dragndrop.client.core;

import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Widgets that implements this interface will be drop zones and only this widgets will accept objects that is dropped.
 * After implementing this interface you can use this code to attach drop handlers to the widget.
 * <p>
 * Register a handler to catch {@link dragndrop.client.core.DropEvent}. DropEvent is fired when something is dropped over current drop zone.
 * </p>
 * <pre>
 * public HandlerRegistration addDropHandler(DropHandler handler){
 *   return addHandler(handler, dragndrop.client.core.DropEvent.TYPE);
 * }
 * </pre>
 * Register a handler to catch {@link dragndrop.client.core.DragOverEvent}. DragOverEvent is fired all time when user drag something over drop zone.
 * <pre>
 * public void addDragOverHandler(DragOverHandler handler) {
 *   addHandler(handler, DragOverEvent.TYPE);
 * }
 * </pre>
 * Register a handler to catch {@link dragndrop.client.core.DragOutEvent}. DragOutEvent is fired when user drag something out of the drop zone.
 * <pre>
 * public void addDragOutHandler(DragOutHandler handler) {
 *   addHandler(handler, DragOutEvent.TYPE);
 * }
 * </pre>
 * Register a handler to catch {@link dragndrop.client.core.DragInEvent}. DragInEvent is fired when user drag something in the drop zone.
 * <pre>
 * public void addDragInHandler(DragInHandler handler) {
 *   addHandler(handler, DragInEvent.TYPE);
 * }
 * </pre>
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface DropZone {  // TODO: can be optimized. if interfaces HasDropHandlers, HasDragInHandlers is created, and this DropZone extends all of them, user will not need to implements all 4 methods if he only need DropHandler!. Fire events will be optimized and searching for drop zones will be improved. Less iterations will be needed when searching a drop zone. One map can do all the yob for find the proper drop zone processing.

  /**
   * Register {@link dragndrop.client.core.DropHandler} that will handle DropEvent.
   *
   * @param handler drop handler.
   */
  HandlerRegistration addDropHandler(DropHandler handler);

  /**
   * Register {@link dragndrop.client.core.DragInHandler}. This handler handle {@link dragndrop.client.core.DragInEvent}.
   *
   * @param handler drag over handler.
   */
  HandlerRegistration addDragInHandler(DragInHandler handler);

  /**
   * Register {@link dragndrop.client.core.DragOutHandler}. This handler handle {@link dragndrop.client.core.DragOutEvent}.
   *
   * @param handler drag out handler.
   */
  HandlerRegistration addDragOutHandler(DragOutHandler handler);

  /**
   * Register {@link dragndrop.client.core.DragOverHandler}. This handler handle {@link dragndrop.client.core.DragOverEvent}.
   *
   * @param handler drag over handler.
   */
  HandlerRegistration addDragOverHandler(DragOverHandler handler);

}
