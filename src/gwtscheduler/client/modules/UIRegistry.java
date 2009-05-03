package gwtscheduler.client.modules;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.client.modules.views.IUIRegistry;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

/**
 * Holds all calendar controllers. Is responsible for assembling the views.
 * 
 * @author malp
 */
public class UIRegistry implements IUIRegistry {

  /** holds the views data */
  private ArrayList<IViewController> views;

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

}