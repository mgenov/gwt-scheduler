package gwtscheduler.client;

import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.client.dragndrop.DragOverlapHandler;
import gwtscheduler.client.dragndrop.Draggable;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragNDropView extends Draggable implements DragNDropPresenter.Display{
  private Label label = new Label("DRAG N DROP");

  public DragNDropView() {
    initWidget(label);
  }

  public HasText getLabel(){
    return label;
  }

  public void addDropHandler(DropHandler handler) {
    addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public void addDragOverlapHandler(DragOverlapHandler handler) {

  }
}
