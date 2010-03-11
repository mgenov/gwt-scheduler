package gwtscheduler.client;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.DragZone;
import gwtscheduler.client.dragndrop.Draggable;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class TicketPresenter implements Draggable{
  public interface Display {

    void setText(String string);

    void setDescription(String text);
  }

  private Display display;

  private TestTask task;

  public TicketPresenter(Display display) {
    this.display = display;
  }

  public TicketPresenter(Display display, TestTask task) {
    this.display = display;
    this.task = task;
    display.setText(task.getTitle());
    display.setDescription(task.getDescription());
  }

  public Widget getDisplay(){
    return (Widget)display;
  }

  public HasMouseDownHandlers getHasMouseDownHandler(){
    return (HasMouseDownHandlers)display;
  }

  @Override
  public Object getDropObject() {
    return task;
  }
}
