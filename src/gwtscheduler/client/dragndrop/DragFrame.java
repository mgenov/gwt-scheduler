package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
class DragFrame implements Frame{
  interface Display {

    HasMouseMoveHandlers getFrameMouseMoveHandlers();

    HasMouseUpHandlers getFrameMouseUpHandlers();

    void setSize(int width, int height);

    void setStyle(String styleName);

    void capture();

    void release();

    int getHeight();

    int getWidth();
   
  }

  private Display display;

  public void bindDisplay(Display display) {
    this.display = display;
  }

  public HasMouseMoveHandlers getFrameMouseMoveHandlers() {
    return display.getFrameMouseMoveHandlers();
  }


  public HasMouseUpHandlers getFrameMouseUpHandlers() {
    return display.getFrameMouseUpHandlers();
  }

  public void setFrameSize(int width, int height){
    display.setSize(width, height);
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

  public void go(DragZone container, int left, int top) {
    container.addWidget((Widget)display, left, top);
  }

  public void captureFrame() {
    display.capture();
  }

  public void releaseFrameCapture() {
    display.release();
  }

  public void removeFrameFromPanel(DragZone container) {
    container.removeWidget((Widget)display);
  }
}
