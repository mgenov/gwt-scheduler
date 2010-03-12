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

  void removeFrameFromDragZone(DragZone container);

  void setWidth(int width);

  void setHeight(int height);

  void dropObject(Object o);

}
