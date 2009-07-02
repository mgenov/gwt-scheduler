package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.decoration.MultipleElementsDecorator;
import gwtscheduler.client.widgets.decorator.MonthLabelDecorator;
import gwtscheduler.client.widgets.view.month.CompositeMonthPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Days;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.gwt.user.client.Element;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthController extends AbstractViewController<CompositeMonthPanel> {

  /** decorator for labels */
  MultipleElementsDecorator<Element> decorator = new MonthLabelDecorator();

  @Override
  protected CompositeMonthPanel createView() {
    return new CompositeMonthPanel();
  }

  public String getTabLabel() {
    return "Month";
  }

  public Interval onNavigateNext() {
    Interval next = getFactory().next().interval();
    adjustVisibleRows(next);
    decorator.decorate(next, getViewWidget());
    return next;
  }

  public Interval onNavigatePrevious() {
    Interval prev = getFactory().previous().interval();
    adjustVisibleRows(prev);
    decorator.decorate(prev, getViewWidget());
    return prev;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.MONTH, date);
    }
    Interval intv = getFactory().interval();
    adjustVisibleRows(intv);
    decorator.decorate(intv, getViewWidget());
    return intv;
  }

  /**
   * Adjusts the number of visible rows, according to the weeks.
   * @param intv the interval that is to show
   */
  protected void adjustVisibleRows(Interval intv) {
    //must show the necessary rows only
    int weeks = (Days.daysIn(intv).getDays() + 1) / 7; //TODO instead of using hard coded value, use conf
    getViewWidget().showRows(weeks);
  }

}
