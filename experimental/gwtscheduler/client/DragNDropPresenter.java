package gwtscheduler.client;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.dragndrop.Dragger;
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

  public void go(Dragger controller, int left, int top) {
    controller.add((Widget) display, this, left, top);
  }

}
