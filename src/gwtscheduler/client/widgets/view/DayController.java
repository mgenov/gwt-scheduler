package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.view.days.MultipleDaysCalendar;
import gwtscheduler.client.widgets.view.days.MultipleDaysPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayController extends GenericViewController<MultipleDaysCalendar> {

  /** number of rows */
  //TODO move to constant file or so
  private static final int Rows = 48;

  /**
   * Default constructor.
   */
  public DayController() {
  }

  @Override
  protected MultipleDaysCalendar createView() {
    return new DayCalendar();
  }

  public String getTabLabel() {
    return "Day";
  }

  public Interval onNavigateNext() {
    //TODO verify that the view is attached
    Interval tp = getFactory().next().interval();
    getDecorator().decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    getDecorator().decorate(period, getViewWidget());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    getDecorator().decorate(period, getViewWidget());
    return period;
  }

  /**
   * Inner class for days calendar.
   * @author malp
   */
  private class DayCalendar extends MultipleDaysCalendar {

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
  private class DayPanel extends MultipleDaysPanel {

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
