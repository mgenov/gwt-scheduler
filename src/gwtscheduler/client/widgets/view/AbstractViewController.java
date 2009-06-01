package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDateGenerator;
import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.views.IViewController;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Abstract class for view controllers.
 * @author malp
 */
public abstract class AbstractViewController<T extends Widget> implements
    IViewController, IEventNavigationListener {

  /** view widget for the controller */
  private T view;

  /** date factory class */
  @Inject
  private IDateGenerator factory;

  /**
   * Default constructor.
   */
  protected AbstractViewController() {
    view = createView();
  }

  /**
   * This method is responsibel for creating the view.
   */
  protected abstract T createView();

  /**
   * Gets the date factory.
   * @return the date factory
   */
  protected IDateGenerator getFactory() {
    return factory;
  }

  public IEventNavigationListener getNavigationListener() {
    return this;
  }

  public T getViewWidget() {
    return view;
  }

}
