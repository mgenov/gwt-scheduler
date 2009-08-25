package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.decorator.MonthLabelDecorator;
import gwtscheduler.client.widgets.view.month.MonthCalendar;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Days;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthController extends GenericViewController<MonthCalendar> {

  /** defines the number of days in a week */
  final int WeekSize;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  private MonthController(AppConfiguration cfg) {
    WeekSize = cfg.daysInWeek();
    decorator = new MonthLabelDecorator();
  }

  @Override
  protected MonthCalendar createView() {
    return new MonthCalendar(this);
  }

  public String getTabLabel() {
    return "Month";
  }

  public Interval onNavigateNext() {
    Interval next = getFactory().next().interval();
    adjustVisibleRows(next);
    getDecorator().decorate(next, getViewWidget());
    return next;
  }

  public Interval onNavigatePrevious() {
    Interval prev = getFactory().previous().interval();
    adjustVisibleRows(prev);
    //    getViewWidget().setGridSize(rows, cols)
    getDecorator().decorate(prev, getViewWidget());
    return prev;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.MONTH, date);
    }
    Interval intv = getFactory().interval();
    adjustVisibleRows(intv);
    getDecorator().decorate(intv, getViewWidget());
    return intv;
  }

  /**
   * Adjusts the number of visible rows, according to the weeks.
   * @param intv the interval that is to show
   */
  protected void adjustVisibleRows(Interval intv) {
    //must show the necessary rows only
    int weeks = (Days.daysIn(intv).getDays() + 1) / WeekSize;
    getViewWidget().showRows(weeks);
  }

}
