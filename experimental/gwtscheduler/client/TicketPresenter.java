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

  public TicketPresenter(Display display) {
    this.display = display;
  }

  public void setInfo(String string){
    display.setText(string);
  }

  public int getDuration(){
    return 4;
  }

  public void dragWith(DragZone dragZone) {
    dragZone.add((HasMouseDownHandlers)display, this);
  }
}
