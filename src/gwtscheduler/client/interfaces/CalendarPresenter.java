package gwtscheduler.client.interfaces;

import com.google.gwt.user.client.ui.Widget;

import gwtscheduler.client.interfaces.navigation.EventNavigationListener;
import net.customware.gwt.presenter.client.Presenter;

/**
 * Defines a calendar controller. Responsible for mediating the view and the
 * listener. For most cases, the implementing class will be the listener itself.
 * @author malp
 */
public interface CalendarPresenter extends Presenter {

  /**
   * Gets the label for the view.
   * @return the label
   */
  String getTabLabel();

  /**
   * Gets the navigation events listener.
   * @return the listener
   */
  EventNavigationListener getNavigationListener();

  /**
   * @return
   */
  Widget getWidgetDisplay();

}
