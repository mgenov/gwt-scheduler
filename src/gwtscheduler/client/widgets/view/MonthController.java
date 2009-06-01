package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;
import gwtscheduler.common.calendar.Interval;

import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthController extends
    AbstractViewController<CompositeMonthPanel> {

  @Override
  protected CompositeMonthPanel createView() {
    return new CompositeMonthPanel();
  }

  public String getTabLabel() {
    return "Month";
  }

  public ITimePeriod onNavigateNext() {
    return getFactory().next().period();
  }

  public ITimePeriod onNavigatePrevious() {
    return getFactory().previous().period();
  }

  public ITimePeriod onNavigateTo(IDate date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(Interval.MONTH, date);
    }
    return getFactory().period();
  }

}
