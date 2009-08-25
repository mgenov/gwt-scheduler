package gwtscheduler.client.widgets.view;

import org.goda.time.Interval;

import gwtscheduler.client.interfaces.ViewController;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.interfaces.navigation.EventNavigationListener;
import gwtscheduler.client.widgets.decorator.DateTimeLabelDecorator;

import com.google.gwt.user.client.Element;
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

  /** elements decorator */
  //TODO use gin to inject
  protected MultipleElementsIntervalDecorator<Element> decorator = new DateTimeLabelDecorator();

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

  /**
   * Gets the labels decorator.
   * @return the labels decorator
   */
  public MultipleElementsIntervalDecorator<Element> getDecorator() {
    return decorator;
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
