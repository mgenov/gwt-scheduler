package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.widgets.view.common.GenericViewController;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysCalendar;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayPresenter extends GenericViewController<AbstractDaysCalendar> {

  /** number of rows */
  //XXX: fix this
  public static final int Rows = AppInjector.GIN.getInjector().getConfiguration().rowsInDay();

  @Day
  @Inject
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayPresenter(@Day AbstractDaysCalendar view) {
    this.view = view;
  }

  public String getTabLabel() {
    return "Day";
  }

  public Interval onNavigateNext() {
    //TODO verify that the view is attached
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }



}
