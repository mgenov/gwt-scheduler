package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
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
}
