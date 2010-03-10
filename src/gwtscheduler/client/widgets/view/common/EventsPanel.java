package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.AppointmentHandler;
import gwtscheduler.client.widgets.common.event.Event;
import gwtscheduler.client.widgets.common.event.HasWidgetResizeHandlers;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Label;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;
import org.goda.time.Interval;

import java.util.ArrayList;
import java.util.List;

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



  static class CalendarEvent {

    interface Display {
        // event view that is displaying event as rounded panel

      void setViewWidth(int width);
      void setViewHeight(int height);


      void setHeaderTitle(String title);
      

    }

    /** The original event that is holding model values and default event behaviour. */
    private final Event event;
    private Display display;

    public CalendarEvent(Event event) {
      this.event = event;
    }

    public void bindDisplay(Display display) {
      this.display = display;
      
      display.setHeaderTitle(event.getTitle());
    }

    /**
     * Sets the width and height of the provided event.
     *
     * @param width the new width to be set
     * @param height the new height to be set
     */
    public void setSize(int width, int height) {
      display.setViewWidth(width);
      display.setViewHeight(height);
    }

    
    /**
     * Attaches current event to the provided absolute panel.
     *
     * Make note that the coupling with the AbsolutePanel is really bad idea, but currently
     * GWT code doesn't provide an interface that wraps the AbsolutePanel method's such as
     * HasWidgets interface of all widgets that are having child widgets.
     * <p/>
     * To re-size the existing event you have to call <code>setSize</code> method. 
     *
     * @param parent the parent panel to which current event will be atached
     * @param left event's left position
     * @param top the top event's top position
     */
    public void go(AbsolutePanel parent, int left, int top) {
      parent.add((Widget)display, left, top);
    }


  }


  class CalendarEventView extends Composite implements CalendarEvent.Display {

    private Label headerTitle = new Label();
    RoundedLinePanel panel = new RoundedLinePanel(RoundedPanel.ALL, 9);

    public CalendarEventView() {      
      panel.setCornerColor("#FAD163");      
      initWidget(panel);

      VerticalPanel vp = new VerticalPanel();
      vp.setStyleName("cbg-RP");
      vp.setWidth("100%");
      vp.add(new Label("test1"));
      vp.add(new Label("test2"));
      vp.add(new Label("test3"));
      vp.add(new Label("test4"));
      vp.add(new Label("test5"));
      
      panel.setWidget(vp);
    }

    @Override
    public void setViewWidth(int width) {
      setWidth(width + "px");
    }

    @Override
    public void setViewHeight(int height) {
      setHeight(height + "px");
    }

    @Override
    public void setHeaderTitle(String title) {
      headerTitle.setText(title);
    }
  }

}
