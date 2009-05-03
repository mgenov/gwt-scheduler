package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.views.IViewController;
import gwtscheduler.client.utils.GenericDateFactory;

import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for view controllers.
 * 
 * @author malp
 */
public abstract class AbstractViewController<T extends Widget> implements
    IViewController, IEventNavigationListener {

  private T view;

  /**
   * Default constructor.
   */
  protected AbstractViewController() {
    view = createView();
  }

  /**
   * Gets the view.
   * 
   * @return the view
   */
  protected T getView() {
    return view;
  }

  /**
   * This method is responsibel for creating the view.
   */
  protected abstract T createView();

  /**
   * Gets the date interval for this controller.
   * 
   * @return the interval type
   */
  protected abstract GenericDateFactory.Interval getInterval();

  public IDateFactory getDateFactory() {
    return new GenericDateFactory(getInterval());
  }

  public IEventNavigationListener getNavigationListener() {
    return this;
  }

  public Widget getViewWidget() {
    return view;
  }

}
