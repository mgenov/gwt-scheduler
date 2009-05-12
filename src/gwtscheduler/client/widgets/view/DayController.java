package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.utils.GenericDateFactory.Interval;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import com.google.inject.Singleton;

/**
 * Controller for days view.
 * 
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

  @Override
  protected Interval getInterval() {
    return Interval.DAY;
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
