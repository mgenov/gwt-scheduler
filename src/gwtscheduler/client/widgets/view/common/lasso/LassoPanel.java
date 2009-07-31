package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.resources.Resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Lasso panel.
 * @author malp
 */
public class LassoPanel extends AbsolutePanel implements MouseDownHandler, MouseMoveHandler, MouseUpHandler {

  /** the lasso subject grid */
  private LassoSubject subject;

  /** indicates if a lasso is being selected or not */
  private boolean isMouseDown = false;

  /**
   * Default constructor.
   */
  public LassoPanel() {
    //style
    addStyleName(Resources.dayWeekCss().lasso());
    DOM.setStyleAttribute(getElement(), "opacity", "0.3");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=30)");

    addDomHandler(this, MouseDownEvent.getType());
    addDomHandler(this, MouseUpEvent.getType());
    addDomHandler(this, MouseMoveEvent.getType());
  }

  @Override
  public void onMouseDown(MouseDownEvent event) {
    isMouseDown = true;
    int x = event.getRelativeX(getElement());
    int y = event.getRelativeX(getElement());
  }

  @Override
  public void onMouseUp(MouseUpEvent event) {
    isMouseDown = false;
  }

  @Override
  public void onMouseMove(MouseMoveEvent event) {
    if (!isMouseDown) {
      return;
    }
  }

  /**
   * Sets the lasso subject.
   * @param subject the subject
   */
  void initialize(LassoSubject subject) {
    this.subject = subject;
  }

}