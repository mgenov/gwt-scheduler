package gwtscheduler.client.modules.views;

import gwtscheduler.client.interfaces.ViewController;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;

import java.util.ArrayList;
import java.util.List;

import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Holds all calendar controllers. Is responsible for assembling the views.
 * @author malp
 */
@Singleton
public class DefaultUIRegistry implements UIManager {

  /** holds the views data */
  private ArrayList<ViewController> views;

  /**
   * Default constructor.
   * @param day the day controller
   * @param week the week controller
   * @param month the month controller
   */
  @Inject
  public DefaultUIRegistry(@Day ViewController day, @Week ViewController week, @Month ViewController month) {
    views = new ArrayList<ViewController>();
    views.add(day);
    views.add(week);
    views.add(month);
  }

  //TODO: navigation could be optimized, if the controller
  //is aware of its own visibility. No need to advance within non-visible controller views

  public void addController(ViewController view) {
    views.add(view);
  }

  public List<ViewController> getControllers() {
    return views;
  }

  public void fireBackNavigation() {
    for (ViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigatePrevious();
    }
  }

  public void fireForwardNavigation() {
    for (ViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigateNext();
    }

  }

  public void fireDateNavigation(ReadableDateTime date) {
    //TODO maybe round the date to the nearest sunday
    for (ViewController controller : getControllers()) {
      controller.getNavigationListener().onNavigateTo(date);
    }
  }

}