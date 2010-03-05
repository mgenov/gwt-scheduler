package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.GenericCalendarDisplay;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.EventNavigationListener;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Generic class for a calendar presenter.
 * @author malp
 */
public abstract class AbstractCalendarPresenter<T extends GenericCalendarDisplay> implements CalendarPresenter,
    EventNavigationListener, ComplexGrid {

  @Inject
  private DateGenerator factory;
  protected Display display;

  /**
   * Default constructor.
   * @param display the display
   * @param eventBus the event bus
   */
  protected AbstractCalendarPresenter( EventBus eventBus) {
    new EventsMediator(this,eventBus);
  }

  @Override
  public Interval getCurrentInterval() {
    return getFactory().interval();
  }

  /**
   * Presenter methods.
   */


  @Override
  public EventNavigationListener getNavigationListener() {
    return this;
  }

  @Override
  public Widget getWidgetDisplay() {
    return (Widget) display;
  }

  public void forceLayout() {
    display.forceLayout();
  }

  /**
   * View Controller methods
   */

  /**
   * Gets the date factory.
   * @return the date factory
   */
  protected DateGenerator getFactory() {
    return factory;
  }

  /**
   * Lasso Subject methods.
   */

  @Override
  public int getWidth() {
    return display.getWidth();
  }

  @Override
  public int getHeight() {
    return display.getHeight();
  }

  @Override
  public abstract Instant getInstantForCell(int[] start);

  /**
   * Gets the duration per cells.
   * @param count the number of cells
   * @return the duration per cells
   */
  protected abstract Duration getDurationPerCells(int count);

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end).plus(getDurationPerCells(1));
    //this is to make sure that [0,0] is at least one cell's duration
    return new Interval(from, to);
  }

  public Display getDisplay() {
    return display;
  }
}
