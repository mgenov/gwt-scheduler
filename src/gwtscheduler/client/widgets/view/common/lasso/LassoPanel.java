package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.resources.Resources;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Lasso panel.
 * @author malp
 */
public class LassoPanel extends AbsolutePanel {

  private int rows;
  private int cols;
  private LassoSubject subject;

  /**
   * Default constructor.
   */
  public LassoPanel() {
    super();
    this.rows = 0;
    this.cols = 0;
    String className = Resources.dayWeekCss().lasso();
    addStyleName(className);

    DOM.setStyleAttribute(getElement(), "opacity", "0.3");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=30)");
  }

  /**
   * Sets the lasso subject.
   * @param subject the subject
   */
  public void setLassoSubject(LassoSubject subject) {
    this.subject = subject;
  }

  public void setRows(int rows) {
    this.rows = rows;
  }

  public void setColumns(int cols) {
    this.cols = cols;
  }

}
