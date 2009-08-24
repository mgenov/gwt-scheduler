package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.RedrawablePanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Lasso panel that extends the redrawable panel in order to support lasso-style
 * selection.
 * @author malp
 */
public abstract class LassoAwarePanel extends RedrawablePanel {

  /** the lasso widget itself */
  private LassoPanel lasso;

  /**
   * Default constructor.
   * @param subject
   */
  public LassoAwarePanel() {
    lasso = new LassoPanel();
    //TODO move to constant file or so
    DOM.setStyleAttribute(lasso.getElement(), "zIndex", "1");
    addToWindow(lasso, 0, 0);
    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        positionLasso(lasso, event);
        resizeLasso(lasso, event);
        //resize events
      }
    });
  }

  /**
   * Inits the lasso.
   * @param strat the strategy
   * @param subject the subject
   */
  protected void initLasso(LassoStrategy strat, LassoSubject subject) {
    lasso.setStrategy(strat);
    lasso.setLassoSubject(subject);
  }

  /**
   * Responsible for positioning the lasso correctly. This method is fired on
   * viewport resize.
   * @param lasso the lasso
   * @param event the last resize event
   */
  protected abstract void positionLasso(Widget lasso, WidgetResizeEvent event);

  /**
   * Responsible for sizing the lasso appropriately.This method is fired on
   * viewport resize.
   * @param lasso the lasso widget
   * @param event the last resize event
   */
  protected abstract void resizeLasso(Widget lasso, WidgetResizeEvent event);

}
