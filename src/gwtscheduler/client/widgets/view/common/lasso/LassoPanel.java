package gwtscheduler.client.widgets.view.common.lasso;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class LassoPanel extends AbsolutePanel {

  public LassoPanel() {
    super();
    DOM.setStyleAttribute(getElement(), "opacity", "0.3");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=30)");
    DOM.setStyleAttribute(getElement(), "background", "red");
  }
}
