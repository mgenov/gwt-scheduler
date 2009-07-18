package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.HasLasso;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.common.lasso.LassoPanel;

import com.google.gwt.user.client.DOM;

/**
 * @author malp
 */
public class LassoAwarePanel extends RedrawablePanel implements HasLasso {

  private LassoPanel lasso;

  /**
   * Default constructor.
   */
  public LassoAwarePanel() {
    super();
    
    lasso = new LassoPanel();
    DOM.setStyleAttribute(lasso.getElement(), "opacity", "0.3");
    DOM.setStyleAttribute(lasso.getElement(), "filter", "alpha(60)");
    DOM.setStyleAttribute(lasso.getElement(), "background", "red");

    addToWindow(lasso, 0, 0);
    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        lasso.setPixelSize(event.width, event.height);
      }
    });
  }

  @Override
  public void setGridSize(int rows, int cols) {
  }

  @Override
  public void startSelection(int startIndex) {
  }

  @Override
  public void stopSelection() {
  }

  @Override
  public void updateSelection(int endIndex) {
  }

}
