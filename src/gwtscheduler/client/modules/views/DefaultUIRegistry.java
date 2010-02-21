package gwtscheduler.client.modules.views;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.widgets.common.CalendarPresenter;

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
  private ArrayList<CalendarPresenter> views;

  /**
   * Default constructor.
   * @param day the day controller
   * @param week the week controller
   * @param month the month controller
   */
  @Inject
  public DefaultUIRegistry(@Day CalendarPresenter day, @Week CalendarPresenter week, @Month CalendarPresenter month) {
    views = new ArrayList<CalendarPresenter>();
    views.add(day);
    views.add(week);
    views.add(month);
  }

  //TODO: navigation could be optimized, if the controller
  //is aware of its own visibility. can defer the advance for non-visible controller views

  public void addController(CalendarPresenter view) {
    views.add(view);
  }

  public List<CalendarPresenter> getControllers() {
    return views;
  }

  public void fireBackNavigation() {
    for (CalendarPresenter controller : getControllers()) {
      controller.getNavigationListener().onNavigatePrevious();
    }
  }

  public void fireForwardNavigation() {
    for (CalendarPresenter controller : getControllers()) {
      controller.getNavigationListener().onNavigateNext();
    }

  }

  public void fireDateNavigation(ReadableDateTime date) {
    for (CalendarPresenter controller : getControllers()) {
      controller.getNavigationListener().onNavigateTo(date);
    }
  }

}