package gwtscheduler.client.teamexample;

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
public class CarView extends Composite implements Car.Display, HasMouseDownHandlers{
  private VerticalPanel mainPanel = new VerticalPanel();
  private Label name = new Label();

  public CarView() {
    mainPanel.add(name);
    mainPanel.setBorderWidth(2);
    initWidget(mainPanel);
  }

  @Override
  public HasText getName() {
    return name;
  }

  public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
    return addDomHandler(handler, MouseDownEvent.getType());
  }
}
