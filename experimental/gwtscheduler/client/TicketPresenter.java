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

    int getDragHeight();

    int getDragWidth();

    HasMouseDownHandlers getDragLabel();
  }

  private Display display;
  private String info;

  public TicketPresenter(Display display) {
    this.display = display;
  }

  public TicketPresenter(Display display, String info) {
    this.display = display;
    setInfo(info);
  }

  public void setInfo(String string){
    info = string;
  }

  public int getDuration(){
    return 1;
  }

  public String getInfo(){
    return info;
  }

  public Widget getDisplay(){
    return (Widget)display;
  }

  public HasMouseDownHandlers getHasMouseDownHandler(){
    return display.getDragLabel();
  }

  @Override
  public Object getDropObject() {
    return this;
  }

  @Override
  public int getWidth() {
    return display.getDragWidth();
  }

  @Override
  public int getHeight() {
    return display.getDragHeight();
  }
}
