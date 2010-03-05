package gwtscheduler.client;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.Draggable;
import gwtscheduler.client.dragndrop.Dragger;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketPresenter implements Draggable{
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

  @Override
  public void go(Dragger dragger, int left, int top) {
    dragger.add((Widget)display, this, left, top);
  }
}
