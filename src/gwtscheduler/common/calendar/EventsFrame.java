package gwtscheduler.common.calendar;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.CursorStyle;
import dragndrop.client.core.Frame;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
//TODO: This frame maybe can be removed and use default frame from DragAndDrob but with different z-index and cursor style (see constructor)
//TODO: Remove from here and build in calendar builder. 
public class EventsFrame implements Frame {
  private Display display;

  public EventsFrame(Display display) {
    this.display = display;
    setZIndex(33);
    setCursorStyle(CursorStyle.POINTER.toString());
  }

  @Override
  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return display.getHasMouseMoveHandlers();
  }

  @Override
  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return display.getHasMouseUpHandlers();
  }

  @Override
  public void setFrameStyle(String styleName) {
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

  @Override
  public void captureFrame() {
    display.capture();
  }

  @Override
  public void releaseFrameCapture() {
    display.release();
  }

  @Override
  public void setWidth(int width) {
    display.setWidth(width);
  }

  @Override
  public void setHeight(int height) {
    display.setHeight(height);
  }

  @Override
  public void dropObject(Object o) {
    // TODO: probably not needed.
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
