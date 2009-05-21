package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * 
 * @author malp
 */
@Week
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
    getFactory().init(IDateFactory.Interval.WEEK, date);
  }

}
