package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

/**
 * @author malp
 */
public class EventWidget extends Composite {

  /**
   * Default constructor.
   */
  public EventWidget() {
    Label label = new Label("ABCD");
    initWidget(label);
  }
}
