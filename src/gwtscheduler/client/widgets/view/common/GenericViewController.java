package gwtscheduler.client.widgets.view.common;

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
  protected T view;

  /** date factory class */
  @Inject
  private DateGenerator factory;

  /**
   * Default constructor.
   */
  protected GenericViewController() {
  }
  
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
