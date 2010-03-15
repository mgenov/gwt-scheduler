package gwtscheduler.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class TicketView2 extends Composite implements TicketPresenter.Display, HasMouseDownHandlers{
  private FlexTable table = new FlexTable();
  private Label taskName = new Label("Task name.");
  private Label taskDescription = new Label("Task description");
  private Label dragLabel = new Label("Drag here");

  public TicketView2() {
    table.setWidget(0, 0, dragLabel);
    table.setWidget(1, 0, taskName);
    table.setWidget(2, 0, taskDescription);
    table.setBorderWidth(2);
    initWidget(table);
    dragLabel.getElement().getStyle().setCursor(Style.Cursor.MOVE);
  }


  public void setText(String string) {
    taskName.setText(string);
  }

  @Override
  public void setDescription(String text) {
    taskDescription.setText(text);
  }

  @Override
  public int getDragHeight() {
    return table.getOffsetHeight();
  }

  @Override
  public int getDragWidth() {
    return table.getOffsetWidth();
  }

  @Override
  public HasMouseDownHandlers getDragLabel() {
    return dragLabel;
  }

  @Override
  public HandlerRegistration addMouseDownHandler(MouseDownHandler mouseDownHandler) {
    return addDomHandler(mouseDownHandler, MouseDownEvent.getType());
  }
}
