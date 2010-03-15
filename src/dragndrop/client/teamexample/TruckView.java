package dragndrop.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class TruckView extends Composite implements Truck.Display, HasMouseDownHandlers{
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

  public HandlerRegistration addMouseDownHandler(MouseDownHandler mouseDownHandler) {
    return addDomHandler(mouseDownHandler, MouseDownEvent.getType());
  }
}
