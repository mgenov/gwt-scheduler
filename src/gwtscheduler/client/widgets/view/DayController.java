package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.view.common.GenericViewController;
import gwtscheduler.client.widgets.view.days.MultipleDaysCalendar;
import gwtscheduler.client.widgets.view.days.MultipleDaysPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayController extends GenericViewController<MultipleDaysCalendar> {

  /** number of rows */
  private static final int Rows = AppInjector.GIN.getInjector().getConfiguration().rowsInDay();

  @Day
  @Inject
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayController(@Day MultipleDaysCalendar view) {
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

  /**
   * Inner class for days calendar.
   * @author malp
   */
  public static class DayCalendar extends MultipleDaysCalendar {

    @Override
    protected MultipleDaysPanel createDaysPanel() {
      return new DayPanel();
    }

    @Override
    protected LassoStrategy getStrategy() {
      return new VerticalLassoStrategy(false);
    }

  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class DayPanel extends MultipleDaysPanel {

    @Override
    protected int getColumns() {
      return 1;
    }

    @Override
    protected int getRows() {
      return Rows; // 24*2
    }

    @Override
    public Interval getIntervalForRange(int[] start, int[] end) {
      Interval i = new Interval(getInstantForCell(start),
          getInstantForCell(end));
      return i;
    }

    @Override
    public Instant getInstantForCell(int[] start) {
      return null;
    }

  }

}
