package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.event.AbstractLassoEvent;
import gwtscheduler.client.widgets.common.event.LassoCancelSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEndSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEventHandler;
import gwtscheduler.client.widgets.common.event.LassoStartSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoUpdateSelectionEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

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
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
  }

}
