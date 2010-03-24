package dragndrop.client.core;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
class DragFrameWidget extends Composite implements DragFrame.Display{
  private Label frame = new Label();
  private String style = "border: 1px dotted;";

  public DragFrameWidget() {
    initWidget(frame);

    frame.getElement().setAttribute("style", style);
  }

  @Override
  public HasMouseMoveHandlers getHasMouseMoveHandlers() {
    return frame;
  }

  @Override
  public HasMouseUpHandlers getHasMouseUpHandlers() {
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
    frame.getElement().getStyle().setProperty("cursor",cursorType);
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
