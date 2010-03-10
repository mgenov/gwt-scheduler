package gwtscheduler.client.teamexample;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.client.dragndrop.DragInEvent;
import gwtscheduler.client.dragndrop.DragOutHandler;
import gwtscheduler.client.dragndrop.DragInHandler;
import gwtscheduler.client.dragndrop.DragOverHandler;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class RaceView extends Composite implements Race.Display{
  private FlexTable table = new FlexTable();

  public RaceView() {
    table.setPixelSize(250, 300);
    table.setBorderWidth(2);
    initWidget(table);
  }

  @Override
  public void addNewTeam(String name, String vehicles) {
    int row = table.getRowCount();
    table.setWidget(row, 0, new Label(name));
    table.setWidget(row, 1, new Label(vehicles));
  }

  @Override
  public HandlerRegistration addDropHandler(DropHandler handler) {
    return addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragInHandler(DragInHandler handler) {
    return addHandler(handler, DragInEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragOutHandler(DragOutHandler handler) {
    return null;
  }

  @Override
  public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
    return null;
  }

}
