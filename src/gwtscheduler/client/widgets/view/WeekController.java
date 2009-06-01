package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.calendar.Interval;

import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * @author malp
 */
@Singleton
public class WeekController extends
    AbstractViewController<AbstractCompositeDaysPanel> {

  protected AbstractCompositeDaysPanel createView() {
    return new AbstractCompositeDaysPanel() {
      @Override
      protected AbstractDayPanel createDayView() {
        return new AbstractDayPanel() {

          @Override
          protected int getColumns() {
            return 7;
          }

          @Override
          protected int getRows() {
            return 48; // 24*2
          }

        };
      }
    };
  }

  public String getTabLabel() {
    return "Week";
  }

  public ITimePeriod onNavigateNext() {
    return getFactory().next().period();
  }

  public ITimePeriod onNavigatePrevious() {
    return getFactory().previous().period();
  }

  public ITimePeriod onNavigateTo(IDate date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(Interval.WEEK, date);
    }
    return getFactory().period();

  }

}
