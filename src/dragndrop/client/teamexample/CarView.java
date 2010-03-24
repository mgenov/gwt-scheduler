package dragndrop.client.teamexample;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)   
 */
public class CarView extends Composite implements Car.Display{
  private VerticalPanel mainPanel = new VerticalPanel();
  private Label dragField = new Label("DRAG HERE");
  private Label name = new Label();

  public CarView() {
    mainPanel.add(dragField);
    mainPanel.add(name);
    mainPanel.setBorderWidth(2);
    initWidget(mainPanel);
  }

  @Override
  public HasText getName() {
    return name;
  }

  public HasMouseDownHandlers getDragField(){
    return dragField;
  }

  @Override
  public int getWidth() {
    return mainPanel.getOffsetWidth();
  }

  @Override
  public int getHeight() {
    return mainPanel.getOffsetHeight();
  }
}
