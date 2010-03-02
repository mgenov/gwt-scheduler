package gwtscheduler.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import gwtscheduler.client.dragndrop.Draggable;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketView2 extends Draggable implements TicketPresenter.Display{
  private FlexTable table = new FlexTable();
  private Label taskName = new Label("Task name.");
  private Label taskDescription = new Label("Task description");
  private TextBox name = new TextBox();
  private TextBox description = new TextBox();
  private TextBox value = new TextBox();

  public TicketView2() {
    table.setWidget(0, 0, taskName);
    table.setWidget(0, 1, name);

    table.setWidget(1, 0, taskDescription);
    table.setWidget(1, 1, description);

    table.setWidget(2, 1, value);

    initWidget(table);
  }


  public void setText(String string) {
    value.setText("");
    value.setText(string);
  }
}
