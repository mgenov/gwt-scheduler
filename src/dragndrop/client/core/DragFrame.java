package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
class DragFrame implements Frame {
  

  private Display display;

  public void bindDisplay(Display display) {
    this.display = display;
  }

  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return display.getHasMouseMoveHandlers();
  }

  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return display.getHasMouseUpHandlers();
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

  @Override
  public void setCursorStyle(String cursorType) {
    display.setCursorStyle(cursorType);
  }

  @Override
  public void setZIndex(int zIndex) {
    display.setZIndex(zIndex);
  }

  @Override
  public String getZIndex() {
    return display.getZIndex();
  }

}
