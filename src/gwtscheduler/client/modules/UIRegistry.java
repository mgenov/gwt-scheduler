package gwtscheduler.client.modules;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.ICalendarController;
import gwtscheduler.client.modules.views.IUIRegistry;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

/**
 * Defines the ui Ioc User Interface Module.
 * 
 * @author malp
 */
public class UIRegistry implements IUIRegistry {

  /** holds the views data */
  private ArrayList<ICalendarController> views;

  @Inject
  public UIRegistry(@Day ICalendarController day,
      @Week ICalendarController week, @Month ICalendarController month) {
    views = new ArrayList<ICalendarController>();
    views.add(day);
    views.add(week);
    views.add(month);
  }

  public void addController(ICalendarController view) {
    views.add(view);
  }

  public List<ICalendarController> getControllers() {
    return views;
  }

}