package gwtscheduler.client;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.Frame;
import gwtscheduler.common.calendar.CalendarFrame;
import gwtscheduler.client.TicketPresenter;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketPresenterFrame implements Frame, CalendarFrame {
  private Frame.Display display;
  private int duration;

  public void bindDisplay(Frame.Display display){
    this.display = display;
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
    display.setWidth(width + "px");
  }

  @Override
  public void setHeight(int height) {
    display.setHeight(height + "px");
  }

  @Override
  public void dropObject(Object o) {
    if(o instanceof TicketPresenter){
      TicketPresenter ticket = (TicketPresenter)o;
      duration = ticket.getDuration();
    }
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

  @Override
  public void onDragOver(int cellWidth, int cellHeight) {
    setWidth(cellWidth);
    setHeight(cellHeight * duration * 2);
  }
}
