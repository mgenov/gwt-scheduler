package gwtscheduler.client;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import dragndrop.client.core.Frame;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketPresenterFrameView extends Composite implements Frame.Display{
  private Label frame = new Label();
  private String style = "border: 1px dotted;";

  public TicketPresenterFrameView() {
    initWidget(frame);

    frame.getElement().setAttribute("style", style);
    setZIndex(44);
    setCursorStyle("not-allowed");
  }

  public HasMouseMoveHandlers getHasMouseMoveHandlers(){
    return frame;
  }

  public HasMouseUpHandlers getHasMouseUpHandlers(){
    return frame;
  }

  @Override
  public void capture() {
    DOM.setCapture(frame.getElement());
  }

  @Override
  public void release() {
    DOM.releaseCapture(frame.getElement());
  }

  @Override
  public void setCursorStyle(String cursorType) {
    this.getElement().getStyle().setProperty("cursor",cursorType);
  }

  @Override
  public void setZIndex(int zIndex) {
    frame.getElement().getStyle().setZIndex(zIndex);
  }

  @Override
  public String getZIndex() {
    return frame.getElement().getStyle().getZIndex();
  }

}
