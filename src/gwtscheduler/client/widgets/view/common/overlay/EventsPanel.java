package gwtscheduler.client.widgets.view.common.overlay;

import gwtscheduler.client.interfaces.uievents.lasso.AbstractLassoEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoCancelSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEndSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEventHandler;
import gwtscheduler.client.interfaces.uievents.lasso.LassoStartSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoUpdateSelectionEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.modules.AppInjector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * @author malp
 */
public class EventsPanel extends AbsolutePanel implements WidgetResizeHandler, LassoEventHandler {

  /**
   * Default constructor.
   */
  public EventsPanel() {
    AppInjector.GIN.getInjector().getEventBus().addHandler(AbstractLassoEvent.getType(), this);
    getElement().getStyle().setPosition(Position.ABSOLUTE);
  }

  @Override
  public void onCancelSelection(LassoCancelSelectionEvent event) {
    GWT.log("onCancelSelection", null);
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    GWT.log("onEndSelection", null);
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
    GWT.log("onStartSelection", null);
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
    GWT.log("onUpdateSelection", null);
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    GWT.log("Should resize events", null);
  }

}
