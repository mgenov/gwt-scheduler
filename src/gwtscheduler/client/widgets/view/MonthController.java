package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.IDateFactory;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

import com.google.gwt.core.client.GWT;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Month
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
    getFactory().init(IDateFactory.Interval.MONTH, date);
  }

}
