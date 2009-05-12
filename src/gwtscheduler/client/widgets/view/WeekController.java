package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.utils.GenericDateFactory.Interval;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

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

  @Override
  protected Interval getInterval() {
    return Interval.WEEK;
  }

  public ITimePeriod onNavigateNext() {
    return null;
  }

  public ITimePeriod onNavigatePrevious() {
    return null;
  }

  public void onNavigateTo(IDate date) {
  }

}
