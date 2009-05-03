package gwtscheduler.client.modules.views;

import java.util.List;

/**
 * Defines a calendar views registry.
 * 
 * @author malp
 */
public interface IUIRegistry {

  /**
   * Gets the views.
   * 
   * @return the views
   */
  List<ICalendarController> getControllers();

  /**
   * Adds a new controller.
   * 
   * @param provider the controller
   */
  void addController(ICalendarController controller);

}
