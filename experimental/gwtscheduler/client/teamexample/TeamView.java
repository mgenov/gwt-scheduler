package gwtscheduler.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.client.dragndrop.DragOutHandler;
import gwtscheduler.client.dragndrop.DragOverEvent;
import gwtscheduler.client.dragndrop.DragOverHandler;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TeamView extends Composite implements Team.Display, HasMouseDownHandlers{
  private FlexTable mainPanel = new FlexTable();
  private Label name = new Label();
  private Label cars = new Label("Cars:");
  private Label trucks = new Label("Trucks:");

  public TeamView() {
    mainPanel.setBorderWidth(1);
    mainPanel.setWidget(0, 0, name);
    mainPanel.setWidget(1, 0, cars);
    mainPanel.setWidget(1, 1, new Label());
    mainPanel.setWidget(2, 1, new Label());
    mainPanel.setWidget(2, 0, trucks);
    initWidget(mainPanel);
  }

  @Override
  public HasText getTeamName() {
    return name;
  }

  @Override
  public void addCarName(String carName) {
    Label label = (Label)mainPanel.getWidget(1, 1);
    String name = label.getText();

    name = name + carName + ", ";

    mainPanel.clearCell(1, 1);
    mainPanel.setWidget(1, 1, new Label(name));
  }

  @Override
  public void addTrackName(String truckName) {
    Label label = (Label)mainPanel.getWidget(2, 1);
    String name = label.getText();

    name = name + truckName + ", ";

    mainPanel.clearCell(2, 1);
    mainPanel.setWidget(2, 1, new Label(name));
  }

  @Override
  public void addDropHandler(DropHandler handler) {
    addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public void addDragOverHandler(DragOverHandler handler) {
    addHandler(handler, DragOverEvent.TYPE);
  }

  @Override
  public void addDragOutHandler(DragOutHandler handler) {
  }

  @Override
  public HandlerRegistration addMouseDownHandler(MouseDownHandler mouseDownHandler) {
    return addDomHandler(mouseDownHandler, MouseDownEvent.getType());
  }
}
