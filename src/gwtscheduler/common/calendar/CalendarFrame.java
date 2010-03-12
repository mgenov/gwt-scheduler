package gwtscheduler.common.calendar;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.DragZone;
import dragndrop.client.core.Frame;
import gwtscheduler.client.TicketPresenter;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarFrame implements Frame {
  public interface Display {
    HasMouseMoveHandlers getHasMouseMoveHandlers();

    HasMouseUpHandlers getHasMouseUpHandlers();

    void setSize(int width, int height);

    void setHeight(int height);

    void setWidth(int width);

    void setStyle(String styleName);

    int getHeight();

    int getWidth();

    void capture();

    void release();

  }

  private Display display;
  private DragZone container;

  public void bindDisplay(Display display){
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
  public void setFrameSize(int width, int height) {
    display.setSize(width, height);
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
  public void go(DragZone container, int left, int top) {
    this.container = container;
    container.addWidget((Widget)display, left, top);
  }

  @Override
  public void setDragZonePosition(int left, int top) {
    container.addWidget((Widget)display, left, top);
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
  public void removeFrameFromDragZone(DragZone container) {
    container.removeWidget((Widget)display);
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
    if(o instanceof TicketPresenter){
      TicketPresenter ticket = (TicketPresenter)o;
      int duration = ticket.getDuration();
      
    }
  }
}
