package gwtscheduler.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.dragndrop.DragZoneImpl;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.navigation.DateViewsTabPanel;

import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    // let's test a registration
    final AppInjector uiResources = AppInjector.GIN.getInjector();
    final UIManager registry = uiResources.getUIRegistry();

    DateViewsTabPanel main = uiResources.getMainPanel();
    //    DateViewsTabPanel main = new DateViewsTabPanel();
    //    for (CalendarPresenter controller : registry.getControllers()) {
    //      main.add(controller);
    //    }

    back = new Button("&laquo;", this);
    forward = new Button("&raquo;", this);
    today = new Button("today", this);
    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);

//    HorizontalPanel tickets = new HorizontalPanel();

    TicketView2 ticketWidget = new TicketView2();
    TicketPresenter ticketPresenter = new TicketPresenter(ticketWidget);
//    tickets.add(ticketWidget);

    AbsolutePanel absolutePanel = new AbsolutePanel();

//    DragZoneImpl dragger = new DragZoneImpl(absolutePanel);
//    dragger.registerDraggable(ticketWidget, ticketPresenter);

    absolutePanel.add(ticketWidget);
    absolutePanel.add(nav);
    absolutePanel.add(main);

    RootPanel.get("nav").add(absolutePanel);
    main.selectTab(0);
    registry.fireDateNavigation(getCurrentDate());
  }

  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    return start;
  }

  public void onClick(ClickEvent event) {
    AppInjector uiResources = AppInjector.GIN.getInjector();
    UIManager registry = uiResources.getUIRegistry();

    if (event.getSource() == back) {
      registry.fireBackNavigation();
    } else if (event.getSource() == forward) {
      registry.fireForwardNavigation();
    } else if (event.getSource() == today) {
      registry.fireDateNavigation(getCurrentDate());
    }

  }
}
