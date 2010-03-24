package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * Instance of this interface is placed on the screen when drag start.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Frame {
  public interface Display {

    HasMouseMoveHandlers getHasMouseMoveHandlers();

    HasMouseUpHandlers getHasMouseUpHandlers();

    void setStyleName(String styleName);

    /**
     * Capture captured frame element.
     * For example use:
     * <pre>
     * Label frame = new Frame();
     * DOM.setCapture(frame.getElement());
     * </pre>
     */
    void capture();

    /**
     * Release captured frame element.
     * For example use:
     * <pre>
     * Label frame = new Frame();
     * DOM.releaseCapture(frame.getElement());
     * </pre>
     */
    void release();

    int getOffsetHeight();

    int getOffsetWidth();

    void setWidth(String width);

    void setHeight(String height);

    void setCursorStyle(String cursorType);

    void setZIndex(int zIndex);

    String getZIndex();
  }

  HasMouseMoveHandlers getFrameMouseMoveHandlers();

  HasMouseUpHandlers getFrameMouseUpHandlers();

  void setFrameStyle(String styleName);

  /**
   * Get current frame height.
   *
   * @return frame height.
   */
  int getHeight();

  /**
   * Get current frame width.
   *
   * @return frame width.
   */
  int getWidth();

  /**
   * Capture captured frame element.
   * For example use:
   * <pre>
   * Label frame = new Frame();
   * DOM.setCapture(frame.getElement());
   * </pre>
   */
  void captureFrame();

  /**
   * Release captured frame element.
   * For example use:
   * <pre>
   * Label frame = new Frame();
   * DOM.releaseCapture(frame.getElement());
   * </pre>
   */
  void releaseFrameCapture();

  /**
   * Set frame width.
   *
   * @param width in pixels.
   */
  void setWidth(int width);

  /**
   * Set frame height.
   *
   * @param height in pixels.
   */
  void setHeight(int height);

  /**
   * Set object who is registered to be dropped when drag starts for some {@link dragndrop.client.core.Draggable} object.
   *
   * @param object drop object.
   */
  void dropObject(Object object);

  /**
   * Return frame display.
   *
   * @return frame widget.
   */
  Widget getWidget();

  /**
   * Set cursor style when cursor is over this frame. Cursor style is changed always when drag starts, stops or some event
   * when dragging ask for changing the cursor style.
   *
   * @param cursorStyle "pointer;" or "not-aloved" or another valid cursor style name.
   */
  void setCursorStyle(String cursorStyle);

  /**
   * Sets z-index attribute for frame. Default is 1.
   *
   * @param zIndex new z-index value.
   */
  void setZIndex(int zIndex);

  /**
   * Return z-index value for current frame.
   *
   * @return z-index value. Default is 1.
   */
  String getZIndex();
}
