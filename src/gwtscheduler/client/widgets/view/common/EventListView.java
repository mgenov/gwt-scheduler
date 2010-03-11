package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.AppointmentHandler;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.common.event.CalendarEventView;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;
import org.goda.time.DateTime;
import org.goda.time.Interval;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventListView extends AbstractGridOverlay implements WidgetResizeHandler, AppointmentHandler {

  /**
   * Default constructor.
   */
  public EventListView() {
    AppInjector.GIN.getInjector().getEventBus().addHandler(AppointmentEvent.getType(), this);
    getElement().getStyle().setPosition(Position.ABSOLUTE);

    CalendarEventView event = new CalendarEventView();
    event.getElement().getStyle().setZIndex(33);
    add(event, 50,50);
    //TODO fix zIndex
    //    getElement().getStyle().setZIndex(Constants.EVENTS_ZINDEX);
  }

  @Override
  public void onAddEvent(AppointmentEvent evt) {
    int[] from = calculateLeftTop(evt.from);
    //    int[] to = calculateLeftTop(evt.to);
    Label label = new Label("test");
    
    CalendarEventView event = new CalendarEventView();
    event.getElement().getStyle().setZIndex(33);
    add(event, from[0], from[1]);

    GWT.log("Test Test", null);
    
//    add(label, from[0], from[1]);
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    //redraw
    super.onResize(event);
  }






}
