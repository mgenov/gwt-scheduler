package gwtscheduler.client.teamexample;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
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
  public void go(DragZone dragZone) {
    dragZone.add((HasMouseDownHandlers) display, this);
  }

  public void go(HasWidgets widget){
    widget.add((Widget)display);
  }

}
