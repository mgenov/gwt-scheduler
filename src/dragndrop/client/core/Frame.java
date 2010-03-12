package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;

/**
 * Represents dragged frame.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface Frame {
  HasMouseMoveHandlers getFrameMouseMoveHandlers();

  HasMouseUpHandlers getFrameMouseUpHandlers();

  void setFrameSize(int width, int height);

  void setFrameStyle(String styleName);

  int getHeight();

  int getWidth();

  void go(DragZone container, int left, int top);

  void setDragZonePosition(int left, int top);

  void captureFrame();

  void releaseFrameCapture();

  void removeFrameFromPanel(DragZone container);

  void addStyleAttribute(String attribute);

  void removeStyle();

  void setWidth(int width);

  void setHeight(int height);
}
