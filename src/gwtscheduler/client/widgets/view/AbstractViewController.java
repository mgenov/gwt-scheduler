package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.interfaces.IEventNavigationListener;
import gwtscheduler.client.modules.IUIInjector;
import gwtscheduler.client.modules.views.IViewController;

import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for view controllers.
 * @author malp
 */
public abstract class AbstractViewController<T extends Widget> implements
    IViewController, IEventNavigationListener {

  /** view widget for the controller */
  private T view;

  /** date factory class */
  private IDateFactory factory;

  /**
   * Default constructor.
   */
  protected AbstractViewController() {
    factory = IUIInjector.GIN.getInjector().getDateFactory();
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
  protected IDateFactory getFactory() {
    return factory;
  }

  public IEventNavigationListener getNavigationListener() {
    return this;
  }

  public T getViewWidget() {
    return view;
  }

}
