package gwtscheduler.client.modules.views;

import java.util.List;

import org.goda.time.ReadableDateTime;

/**
 * Defines a calendar controllers registry.
 * @author malp
 */
public interface IUIRegistry {

  /**
   * Gets the views.
   * @return the views
   */
  List<IViewController> getControllers();

  /**
   * Adds a new controller.
   * @param provider the controller
   */
  void addController(IViewController controller);

  /**
   * Fires the back navigation event.
   */
  void fireBackNavigation();

  /**
   * Fires the forward navigation event.
   */
  void fireForwardNavigation();

  /**
   * Fires the navigation event that goes to a specific date.
   * @param date the date
   */
  void fireDateNavigation(ReadableDateTime date);

}
