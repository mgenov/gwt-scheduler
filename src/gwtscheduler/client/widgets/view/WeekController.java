package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

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

  public Interval onNavigateNext() {
    return getFactory().next().interval();
  }

  public Interval onNavigatePrevious() {
    return getFactory().previous().interval();
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.WEEK, date);
    }
    return getFactory().interval();

  }

}
