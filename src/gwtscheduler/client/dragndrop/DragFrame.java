package gwtscheduler.client.dragndrop;

import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragFrame {
  interface Display {

    HasMouseMoveHandlers getFrameMouseMoveHandlers();

    HasMouseUpHandlers getFrameMouseUpHandlers();

    void setSize(int width, int height);

    void setStyle(String styleName);
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
}
