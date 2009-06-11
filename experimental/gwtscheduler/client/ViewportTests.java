package gwtscheduler.client;

import gwtscheduler.client.interfaces.ViewController;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.nav.DateViewsTabPanel;

import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today;
  Label time;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    // let's test a registration
    AppInjector uiResources = AppInjector.GIN.getInjector();
    UIManager registry = uiResources.getUIRegistry();
    // registry.addController(new DummyMonthController());

    DateViewsTabPanel main = new DateViewsTabPanel();
    // the registry will be pre-filled with default controllers
    // for day, week and month, plus our own dummy controller
    for (ViewController controller : registry.getControllers()) {
      main.add(controller);
    }

    back = new Button("<<", this);
    forward = new Button(">>", this);
    today = new Button("today", this);
    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);

    RootPanel.get().add(nav);
    RootPanel.get().add(new HTML("<BR/>"));
    RootPanel.get().add(main);
    main.selectTab(0);

    registry.fireDateNavigation(getCurrentDate());
  }

  protected ReadableDateTime getCurrentDate() {
    AppConfiguration cfg = AppInjector.GIN.getInjector().getConfiguration();

    MutableDateTime start = new MutableDateTime();
    int firstDay = cfg.getStartDayOfWeek();
    while (start.getDayOfWeek() != firstDay) {
      start.addDays(-1);
    }
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
