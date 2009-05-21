package gwtscheduler.client;

import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.IUIInjector;
import gwtscheduler.client.modules.views.IUIRegistry;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.nav.DateViewsTabPanel;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
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

    // let's test a registration
    IUIInjector uiResources = IUIInjector.GIN.getInjector();
    IUIRegistry registry = uiResources.getUIRegistry();
    registry.addController(new DummyMonthController());

    DateViewsTabPanel main = new DateViewsTabPanel();
    // the registry will be pre-filled with default controllers
    // for day, week and month, plus our own dummy controller
    for (IViewController controller : registry.getControllers()) {
      main.add(controller);
    }

    main.selectTab(0);
    RootPanel.get("calendar-main").add(main);
  }

  /**
   * Dummy class.
   * 
   * @author malp
   */
  private static class DummyMonthController implements IViewController,
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
