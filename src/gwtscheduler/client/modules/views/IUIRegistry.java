package gwtscheduler.client.modules.views;

import java.util.List;

/**
 * Defines a calendar controllers registry.
 * 
 * @author malp
 */
public interface IUIRegistry {

  /**
   * Gets the views.
   * 
   * @return the views
   */
  List<IViewController> getControllers();

  /**
   * Adds a new controller.
   * 
   * @param provider the controller
   */
  void addController(IViewController controller);

}
