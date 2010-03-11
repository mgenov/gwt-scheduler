package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
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
public class EventListView extends AbstractGridOverlay implements EventList.Display {

  /**
   * Default constructor.
   */
  public EventListView() {

    // why this need to be set when AbstractGridOverlay is an absolute panel ???
    getElement().getStyle().setPosition(Position.ABSOLUTE);

    //TODO: Remove this event after layout is completed
    CalendarEventView event = new CalendarEventView();
    event.getElement().getStyle().setZIndex(33);
    add(event, 50,50);    
  }

  @Override
  public AbsolutePanel asWidget() {
    return this;
  }

  @Override
  public int[] getCellPosition(int x, int y) {
    return calculateCellPosition(x, y);
  }
}
