package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.resources.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Lasso panel.
 * @author malp
 */
public class LassoPanel extends AbsolutePanel {

  private LassoSubject subject;

  /**
   * Default constructor.
   */
  public LassoPanel() {
    //style
    addStyleName(Resources.dayWeekCss().lasso());
    DOM.setStyleAttribute(getElement(), "opacity", "0.3");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=30)");

    addDomHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        assert subject != null : "Lasso subject was not properly initialized. Did you forget to call initialize()?";

        GWT.log("rows: " + subject.getRowNum() + ", cols:" + subject.getColNum(), null);
      }
    }, ClickEvent.getType());
  }

  /**
   * Sets the lasso subject.
   * @param subject the subject
   */
  void initialize(LassoSubject subject) {
    this.subject = subject;
  }

}
