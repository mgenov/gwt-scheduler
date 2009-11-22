package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.view.DayPresenter;

import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class DayCalendar extends AbstractDaysCalendar {

  @Override
  protected AbstractDaysPanel createDaysPanel() {
    return new DayPanel();
  }

  @Override
  protected LassoStrategy getStrategy() {
    return new VerticalLassoStrategy(false);
  }
  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class DayPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 1;
    }

    @Override
    protected int getRows() {
      return DayPresenter.Rows; // 24*2
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