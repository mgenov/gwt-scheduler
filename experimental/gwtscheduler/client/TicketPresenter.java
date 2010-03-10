package gwtscheduler.client;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import gwtscheduler.client.dragndrop.DragZone;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class TicketPresenter {
  public interface Display {

    void setText(String string);
  }

  private Display display;
  private String info;

  public TicketPresenter(Display display) {
    this.display = display;
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

  public void dragWith(DragZone dragZone) {
    dragZone.add((HasMouseDownHandlers)display, this);
  }
}
