package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.utils.GenericDateFactory.Interval;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

@Month
public class MonthController extends
    AbstractViewController<CompositeMonthPanel> {

  @Override
  protected CompositeMonthPanel createView() {
    return new CompositeMonthPanel();
  }

  public String getTabLabel() {
    return "Month";
  }

  @Override
  protected Interval getInterval() {
    return Interval.MONTH;
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
