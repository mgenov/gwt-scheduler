package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.view.days.MultipleDaysCalendar;
import gwtscheduler.client.widgets.view.days.MultipleDaysPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * @author malp
 */
@Singleton
public class WeekController extends GenericViewController<MultipleDaysCalendar> {
  /** number of rows */
  private static final int Rows = AppInjector.GIN.getInjector().getConfiguration().rowsInDay();

  @Inject
  @Week
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  protected WeekController() {
  }

  protected MultipleDaysCalendar createView() {
    return new WeekCalendar();
  }

  public String getTabLabel() {
    return "Week";
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    //TODO verify that the view is attached
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      MutableDateTime copy = date.toMutableDateTime();
      AppConfiguration cfg = AppInjector.GIN.getInjector().getConfiguration();
      //adjust to first day of week
      int firstDay = cfg.startDayOfWeek();
      while (copy.getDayOfWeek() != firstDay) {
        copy.addDays(-1);
      }
      getFactory().init(IntervalType.WEEK, copy);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getViewWidget());
    return period;
  }

  /**
   * Inner class for days calendar.
   * @author malp
   */
  private static class WeekCalendar extends MultipleDaysCalendar {

    @Override
    protected MultipleDaysPanel createDaysPanel() {
      return new WeekPanel();
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
  private static class WeekPanel extends MultipleDaysPanel {

    @Override
    protected int getColumns() {
      return 7;
    }

    @Override
    protected int getRows() {
      return Rows;
    }

    @Override
    public Interval getIntervalForRange(int[] start, int[] end) {
      Interval i = new Interval(getInstantForCell(start),
          getInstantForCell(end));
      return i;
    }

    @Override
    public Instant getInstantForCell(int[] start) {
      // TODO XXX implement me
      return null;
    }
  }
}
