package dragndrop.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import dragndrop.client.core.Draggable;

/**
 * Example when presenter is registered as draggable.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class Car implements Draggable {
  public interface Display {
    HasText getName();

    HasMouseDownHandlers getDragField();

    int getWidth();

    int getHeight();
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
  public HasMouseDownHandlers getHasMouseDownHandler() {
    return display.getDragField();
  }

  @Override
  public Object getDropObject() {
    return this;
  }

  @Override
  public int getWidth() {
    return display.getWidth();
  }

  @Override
  public int getHeight() {
    return display.getHeight();
  }

  @Override
  public Widget getSourceWidget() {
    return (Widget)display;
  }
}
