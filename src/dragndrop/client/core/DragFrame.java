package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * Default dragged frame over drag zone.
 * 
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
    display.setStyleName(styleName);
  }

  @Override
  public int getHeight() {
    return display.getOffsetHeight();
  }

  @Override
  public int getWidth() {
    return display.getOffsetWidth();
  }

  public void captureFrame() {
    display.capture();
  }

  public void releaseFrameCapture() {
    display.release();
  }

  @Override
  public void setWidth(int frameWidth) {
    display.setWidth(frameWidth + "px");
  }

  @Override
  public void setHeight(int frameHeight) {
    display.setHeight(frameHeight + "px");
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
