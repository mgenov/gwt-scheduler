package gwtscheduler.client.teamexample;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.DragZone;
import gwtscheduler.client.dragndrop.Draggable;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Truck implements Draggable{
  public interface Display {
    HasText getName();
  }

  private Display display;

  public Truck(Display display, String name) {
    this.display = display;
    display.getName().setText(name);
  }

  public String getName() {
    return display.getName().getText();
  }

  @Override
  public void go(DragZone dragZone, int left, int top) {
    dragZone.add((Widget)display, this, left, top);
  }
}
