package gwtscheduler.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketView2 extends Composite implements TicketPresenter.Display{
  private VerticalPanel panel = new VerticalPanel();


  public TicketView2() {
    initWidget(panel);
  }


  public void setText(String string) {
    panel.add(new Label(string));
  }
}
