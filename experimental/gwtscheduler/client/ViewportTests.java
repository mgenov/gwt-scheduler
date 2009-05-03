package gwtscheduler.client;

import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.IUIInjector;
import gwtscheduler.client.modules.views.ICalendarController;
import gwtscheduler.client.modules.views.IUIRegistry;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.nav.DateViewsTabPanel;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    IUIInjector uiResources = GWT.create(IUIInjector.class);
    IUIRegistry registry = uiResources.getUIRegistry();

    // let's test a registration
    registry.addController(new DummyMonthController());

    TabPanel main = new DateViewsTabPanel();
    // hopefully the registr will be pre-filled with default controllers
    // for day, week and month
    for (ICalendarController controller : registry.getControllers()) {
      main.add(controller.getViewWidget(), controller.getTabLabel());
    }

    main.selectTab(0);
    RootPanel.get("calendar-main").add(main);
  }

  /**
   * Dummy class.
   * 
   * @author malp
   */
  private static class DummyMonthController implements ICalendarController,
      IEventNavigationListener {

    public ITimePeriod onNavigateNext() {
      return null;
    }

    public ITimePeriod onNavigatePrevious() {
      return null;
    }

    public void onNavigateTo(IDate date) {
    }

    public IEventNavigationListener getNavigationListener() {
      return this;
    }

    public String getTabLabel() {
      return "month";
    }

    public Widget getViewWidget() {
      return new CompositeMonthPanel();
    }

  }
}
