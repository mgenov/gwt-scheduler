package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.view.common.RedrawablePanel;
import gwtscheduler.client.widgets.view.common.overlay.CalendarEventsPanel;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Lasso panel that extends the redrawable panel in order to support lasso-style
 * selection.
 * @author malp
 */
//TODO migrate to MVP
public abstract class LassoAwarePanel extends RedrawablePanel {

  /** the lasso widget itself */
  private LassoPanel lasso;
  /**the redraw panel*/
  private CalendarEventsPanel eventsPanel;

  /**
   * Default constructor.
   * @param subject
   */
  public LassoAwarePanel() {
    eventsPanel = new CalendarEventsPanel();
    
    lasso = new LassoPanel();
    lasso.addLassoHandler(eventsPanel);
    
    DOM.setIntStyleAttribute(lasso.getElement(), "zIndex", Constants.LASSO_ZINDEX);
    addToWindow(lasso, 0, 0);
    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        positionLasso(lasso, event);
        resizeLasso(lasso, event);
      }
    });
  }

  /**
   * Inits the lasso.
   * @param strat the strategy
   * @param subject the subject
   */
  public void initLasso(LassoStrategy strat, LassoSubject subject) {
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
