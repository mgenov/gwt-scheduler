package gwtscheduler.client;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.DragZone;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.dragndrop.DropZone;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDropPresenter {
  public interface Display extends DropZone {
    HasText getLabel();
  }

  private Display display;
  private int dropped = 0;
  private int accepted = 0;

  public DragNDropPresenter(final Display display) {
    this.display = display;

    display.addDropHandler(new DropHandler() {
      public void onDrop(DropEvent event) {
        DragNDropPresenter presenter = (DragNDropPresenter) event.getDroppedObject();
        accepted++;
        display.getLabel().setText("Dropped: " + dropped + " accepted: " + accepted);
        presenter.dropped();
      }
    });
  }

  private void dropped() {
    dropped++;
    display.getLabel().setText("Dropped: " + dropped + " accepted: " + accepted);
  }

  public void go(DragZone controller) {
    controller.add((HasMouseDownHandlers) display, this);
  }

}
