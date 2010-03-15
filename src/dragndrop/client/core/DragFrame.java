package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
class DragFrame implements Frame {
  interface Display {

    HasMouseMoveHandlers getFrameMouseMoveHandlers();

    HasMouseUpHandlers getFrameMouseUpHandlers();

    void setStyle(String styleName);

    void capture();

    void release();

    int getHeight();

    int getWidth();

    void setWidth(int width);

    void setHeight(int height);
  }

  private Display display;

  public void bindDisplay(Display display) {
    this.display = display;
  }

  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return display.getFrameMouseMoveHandlers();
  }

  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return display.getFrameMouseUpHandlers();
  }

  public void setFrameStyle(String styleName){
    display.setStyle(styleName);
  }

  @Override
  public int getHeight() {
    return display.getHeight();
  }

  @Override
  public int getWidth() {
    return display.getWidth();
  }

  public void captureFrame() {
    display.capture();
  }

  public void releaseFrameCapture() {
    display.release();
  }

  @Override
  public void setWidth(int frameWidth) {
    display.setWidth(frameWidth);
  }

  @Override
  public void setHeight(int frameHeight) {
    display.setHeight(frameHeight);
  }

  @Override
  public void dropObject(Object o) {
  }

  @Override
  public Widget getWidget() {
    return (Widget)display;
  }

}
