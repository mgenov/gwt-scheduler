package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * When some object implements this interface, object is draggable and can have more extras for manipulation. If someone
 * want to drag and drop only widgets he don't need to implements this interface. (But its good to implement). In case
 * dragged object need to have place where can be dragged (for example header on the window) this interface must be
 * implemented. Dropped object can be any object that will be dropped.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  /**
   * Returns object that is instance of HasMOuseDownHandlers. This object will be used for dragging.
   *
   * @return HasMouseDownHandlers.
   */
  HasMouseDownHandlers getHasMouseDownHandler();

  /**
   * Object thet will be dropped when drag stops.
   *
   * @return
   */
  Object getDropObject();

  /**
   * Get width of the frame that will be dragged.
   *
   * @return preferred frame width.
   */
  int getWidth();

  /**
   * Get height of the frame that will be dragged.
   *
   * @return preferred frame height.
   */
  int getHeight();

  /**
   * Get widget on the dropped object. Can be draggable widget or another widget that will be treated like the source
   * for widget dragging.
   *
   * @return source widget.
   */
  Widget getSourceWidget();
}
