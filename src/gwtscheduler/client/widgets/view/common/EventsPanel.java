package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.AppointmentHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Label;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventsPanel extends AbstractGridOverlay implements WidgetResizeHandler, AppointmentHandler {

  /**
   * Default constructor.
   */
  public EventsPanel() {
    AppInjector.GIN.getInjector().getEventBus().addHandler(AppointmentEvent.getType(), this);
    getElement().getStyle().setPosition(Position.ABSOLUTE);
    //TODO fix zIndex
    //    getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
  }

  @Override
  public void onAddEvent(AppointmentEvent evt) {
    int[] from = calculateLeftTop(evt.from);
    //    int[] to = calculateLeftTop(evt.to);
    Label label = new Label("test");
    add(label, from[0], from[1]);
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    //redraw
    super.onResize(event);
  }

  public int[] getCellPosition(int endX, int endY) {
    return calculateCellPosition(endX, endY);
  }

  public void addWidget(Widget sourceWidget, int[] cell) {
    int[] leftTopPoint = calculateLeftTop(cell);
    this.add(sourceWidget, leftTopPoint[0], leftTopPoint[1]);
    sourceWidget.getElement().getStyle().setZIndex(50);
  }

  
}
