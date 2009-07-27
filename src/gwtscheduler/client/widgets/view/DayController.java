package gwtscheduler.client.widgets.view;

import gwtscheduler.client.widgets.view.days.MultipleDaysCalendar;
import gwtscheduler.client.widgets.view.days.MultipleDaysPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayController extends GenericViewController<MultipleDaysCalendar> {

  /** number of rows */
  private final int Rows = 48;

  @Override
  protected MultipleDaysCalendar createView() {
    return new MultipleDaysCalendar() {
      @Override
      protected MultipleDaysPanel createDaysPanel() {
        return new MultipleDaysPanel() {

          @Override
          protected int getColumns() {
            return 1;
          }

          @Override
          protected int getRows() {
            return Rows; // 24*2
          }

        };
      }
    };
  }

  public String getTabLabel() {
    return "Day";
  }

  public Interval onNavigateNext() {
    //TODO verify that the view is attached
    Interval tp = getFactory().next().interval();
    getDecorator().decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    getDecorator().decorate(period, getViewWidget());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    getDecorator().decorate(period, getViewWidget());
    return period;
  }

}
