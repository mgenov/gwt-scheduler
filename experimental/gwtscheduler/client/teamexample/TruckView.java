package gwtscheduler.client.teamexample;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TruckView extends Composite implements Truck.Display{
  private VerticalPanel mainPanel = new VerticalPanel();
  private Label label = new Label();

  public TruckView() {
    mainPanel.setBorderWidth(2);
    mainPanel.add(label);
    initWidget(mainPanel);
  }

  @Override
  public HasText getName() {
    return label;
  }
}
