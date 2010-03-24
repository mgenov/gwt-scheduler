package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * When some object implements this interface, object became draggable and can have more extras for manipulation. If someone
 * want to drag and drop only widgets he don't need to implements this interface.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  /**
   * Returns object that is instance of HasMOuseDownHandlers. On this object is attached MouseDownHandler and dragging starts.
   *
   * @return HasMouseDownHandlers.
   */
  HasMouseDownHandlers getHasMouseDownHandler();

  /**
   * Object thet will be dropped when drag stops.
   *
   * @return drop object.
   */
  Object getDropObject();

  /**
   * Get width on the dragged object. This value is set for width on dragged frame.
   *
   * @return preferred frame width.
   */
  int getWidth();

  /**
   * Get height on dragged object. This value is set for height on the dragged frame.
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
