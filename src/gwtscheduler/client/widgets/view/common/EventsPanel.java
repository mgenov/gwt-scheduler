package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.AppointmentHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

/**
 * This class is responsible for displaying events.
 *
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

  public void addTicket(Widget widget, int[] pos) {
    int[] from = calculateLeftTop(pos);
    Label label = new Label("WAZAAAAP");
//    GWT.log("0: " + from[0] + " 1: " + from[1], null);
    add(label, from[0], from[1]);
    label.addStyleName("dragFrame");
    GWT.log("Added label", null);
    DOM.setIntStyleAttribute(label.getElement(), "zIndex", 500);
  }
}
