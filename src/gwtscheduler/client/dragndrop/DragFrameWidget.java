package gwtscheduler.client.dragndrop;

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

  public DragFrameWidget() {
    initWidget(frame);

    frame.setStyleName("dragFrame");
  }

  @Override
  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return frame;
  }

  @Override
  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return frame;
  }

  @Override
  public void setSize(int width, int height) {
    frame.setPixelSize(width, height);
  }

  @Override
  public void setStyle(String styleName) {
    frame.setStyleName(styleName);
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
  public int getHeight() {
    return frame.getOffsetHeight();
  }

  @Override
  public int getWidth() {
    return frame.getOffsetWidth();
  }

}
