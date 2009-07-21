package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.HasLasso;
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
public abstract class LassoAwarePanel extends RedrawablePanel implements HasLasso {

  private LassoPanel lasso;

  /**
   * Default constructor.
   */
  public LassoAwarePanel() {
    super();

    lasso = new LassoPanel();
    DOM.setStyleAttribute(lasso.getElement(), "zIndex", "1");
    addToWindow(lasso, 0, 0);
    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        positionLasso(lasso);
        resizeLasso(lasso, event);
      }
    });
  }

  /**
   * Should position the lasso correctly.
   * @param lasso the lasso
   */
  protected abstract void positionLasso(Widget lasso);

  /**
   * Responsible for sizing the lasso appropriately.
   * @param lasso the lasso
   * @param event the last resize event
   */
  protected abstract void resizeLasso(Widget lasso, WidgetResizeEvent event);

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
