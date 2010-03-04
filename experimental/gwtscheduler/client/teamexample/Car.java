package gwtscheduler.client.teamexample;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.Draggable;
import gwtscheduler.client.dragndrop.Dragger;

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
  public void go(Dragger dragger, int left, int top) {
    dragger.add((Widget) display, this, left, top);
  }
}
