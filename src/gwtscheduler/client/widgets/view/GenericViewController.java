package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.ViewController;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.interfaces.navigation.EventNavigationListener;

import org.goda.time.Interval;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Abstract class for view controllers.
 * @author malp
 */
public abstract class GenericViewController<T extends Widget> implements
    ViewController, EventNavigationListener {

  /** view widget for the controller */
  private T view;

  /** date factory class */
  @Inject
  private DateGenerator factory;

  /**
   * Default constructor.
   */
  protected GenericViewController() {
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

  @Override
  public EventNavigationListener getNavigationListener() {
    return this;
  }

  @Override
  public T getViewWidget() {
    return view;
  }

  @Override
  public Interval getCurrentInterval() {
    return getFactory().interval();
  }

}
