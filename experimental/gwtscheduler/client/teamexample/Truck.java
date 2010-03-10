package gwtscheduler.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.DragZone;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class Truck {
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

  public void go(DragZone dragZone) {
    dragZone.add((HasMouseDownHandlers)display, this);
  }

  public void go(HasWidgets widget){
    widget.add((Widget)display);
  }

}
