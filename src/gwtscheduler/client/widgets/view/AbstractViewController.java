package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.client.interfaces.EventNavigationListener;
import gwtscheduler.client.interfaces.ViewController;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Abstract class for view controllers.
 * @author malp
 */
public abstract class AbstractViewController<T extends Widget> implements
    ViewController, EventNavigationListener {

  /** view widget for the controller */
  private T view;

  /** date factory class */
  @Inject
  private DateGenerator factory;

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
  protected DateGenerator getFactory() {
    return factory;
  }

  public EventNavigationListener getNavigationListener() {
    return this;
  }

  public T getViewWidget() {
    return view;
  }

}
