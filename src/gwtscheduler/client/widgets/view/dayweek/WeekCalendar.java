package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;

import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class WeekCalendar extends AbstractDaysCalendar {

  @Override
  protected AbstractDaysPanel createDaysPanel() {
    return new WeekPanel();
  }

  @Override
  protected LassoStrategy getStrategy() {
    return new VerticalLassoStrategy(false);
  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class WeekPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 7;
    }

    @Override
    protected int getRows() {
      return AppInjector.GIN.getInjector().getConfiguration().rowsInDay();
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