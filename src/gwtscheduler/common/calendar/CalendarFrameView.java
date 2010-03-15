package gwtscheduler.common.calendar;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarFrameView extends Composite implements CalendarFrame.Display{
  private Label frame = new Label();
  private String style = "border: 1px dotted;";

  public CalendarFrameView() {
    initWidget(frame);

    frame.getElement().setAttribute("style", style);
  }

  public HasMouseMoveHandlers getHasMouseMoveHandlers(){
    return frame;
  }

  public HasMouseUpHandlers getHasMouseUpHandlers(){
    return frame;
  }

  @Override
  public void setSize(int width, int height) {
    frame.setPixelSize(width, height);
  }

  @Override
  public void setHeight(int height) {
    frame.setHeight(height + "px");
  }

  @Override
  public void setWidth(int width) {
    frame.setWidth(width + "px");
  }

  @Override
  public void setStyle(String styleName) {
    frame.setStyleName(styleName);
  }

  @Override
  public int getHeight() {
    return frame.getOffsetHeight();
  }

  @Override
  public int getWidth() {
    return frame.getOffsetWidth();
  }

  @Override
  public void capture() {
    DOM.setCapture(frame.getElement());
  }

  @Override
  public void release() {
    DOM.releaseCapture(frame.getElement());
  }

}
