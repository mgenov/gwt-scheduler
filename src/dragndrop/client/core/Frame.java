package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * Represents dragged frame.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public interface Frame<T> {

  HasMouseMoveHandlers getFrameMouseMoveHandlers();

  HasMouseUpHandlers getFrameMouseUpHandlers();

  void setFrameStyle(String styleName);

  int getHeight();

  int getWidth();

  void captureFrame();

  void releaseFrameCapture();

  void setWidth(int width);

  void setHeight(int height);

  void dropObject(Object o);

  Widget getWidget();

  void setCursorStyle(String cursorType);
}
