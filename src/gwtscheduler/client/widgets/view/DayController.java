package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.calendar.Interval;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Day
@Singleton
public class DayController extends
    AbstractViewController<AbstractCompositeDaysPanel> {

  @Override
  protected AbstractCompositeDaysPanel createView() {
    return new AbstractCompositeDaysPanel() {
      @Override
      protected AbstractDayPanel createDayView() {
        return new AbstractDayPanel() {

          @Override
          protected int getColumns() {
            return 1;
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
    return "Day";
  }

  public ITimePeriod onNavigateNext() {
    getFactory().next();
    ITimePeriod period = getFactory().period();
    GWT.log("Period: " + period, null);
    return period;
  }

  public ITimePeriod onNavigatePrevious() {
    getFactory().previous();
    ITimePeriod period = getFactory().period();
    GWT.log("Period: " + period, null);
    return period;
  }

  public void onNavigateTo(IDate date) {
    getFactory().init(Interval.DAY, date);
  }

}
