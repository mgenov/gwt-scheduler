package gwtscheduler.client.interfaces.uievents.navigation;

import gwtscheduler.common.calendar.IDate;

import com.google.gwt.event.shared.EventHandler;

/**
 * Navigation events.
 * @author malp
 */
public interface INavigationHandler extends EventHandler {

  void onNavigateBack();

  void onNavigateForward();

  void onNavigateCurrent(IDate date);

}
