package gwtscheduler.common.calendar;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.Frame;
import gwtscheduler.common.calendar.CalendarFrame;
import gwtscheduler.client.TicketPresenter;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarFrameImpl implements Frame, CalendarFrame {
  public interface Display {
    HasMouseMoveHandlers getHasMouseMoveHandlers();

    HasMouseUpHandlers getHasMouseUpHandlers();

    void setHeight(int height);

    void setWidth(int width);

    void setStyle(String styleName);

    int getHeight();

    int getWidth();

    void capture();

    void release();

  }

  private Display display;
  private int duration;

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
    if(o instanceof TicketPresenter){ // TODO: use for calendar event or something like that.
      TicketPresenter ticket = (TicketPresenter)o;
      duration = ticket.getDuration();
    }
  }

  @Override
  public Widget getWidget() {
    return (Widget)display;
  }

  @Override
  public void onDragOver(int cellWidth, int cellHeight) {
    setWidth(cellWidth);
    setHeight(cellHeight * duration * 2);
  }
}
