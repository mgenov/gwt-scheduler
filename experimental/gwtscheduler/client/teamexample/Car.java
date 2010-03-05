package gwtscheduler.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.Draggable;
import gwtscheduler.client.dragndrop.DragZone;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Car implements Draggable{
  public interface Display {
    HasText getName();
  }

  private Display display;

  public Car(Display display, String name) {
    this.display = display;
    display.getName().setText(name);
  }

  public String getName() {
    return display.getName().getText();
  }

  @Override
  public void go(DragZone dragZone, int left, int top) {
    dragZone.add((HasMouseDownHandlers) display, this, left, top);
  }
}
