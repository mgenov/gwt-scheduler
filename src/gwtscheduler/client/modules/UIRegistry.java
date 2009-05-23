package gwtscheduler.client.modules;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.IUIRegistry;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.common.calendar.IDate;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

/**
 * Holds all calendar controllers. Is responsible for assembling the views.
 * @author malp
 */
public class UIRegistry implements IUIRegistry {

  /** holds the views data */
  private ArrayList<IViewController> views;

  /**
   * Default constructor.
   * @param day the day controller
   * @param week the week controller
   * @param month the month controller
   */
  @Inject
  public UIRegistry(@Day IViewController day, @Week IViewController week,
      @Month IViewController month) {
    views = new ArrayList<IViewController>();
    views.add(day);
    views.add(week);
    views.add(month);
  }

  public void addController(IViewController view) {
    views.add(view);
  }

  public List<IViewController> getControllers() {
    return views;
  }

  public void fireBackNavigation() {
    for (IViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigatePrevious();
    }
  }

  public void fireForwardNavigation() {
    for (IViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigateNext();
    }

  }

  public void fireDateNavigation(IDate date) {
    for (IViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigateTo(date);
    }
  }

}