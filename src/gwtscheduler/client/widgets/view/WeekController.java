package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.view.days.MultipleDaysCalendar;
import gwtscheduler.client.widgets.view.days.MultipleDaysPanel;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * @author malp
 */
@Singleton
public class WeekController extends GenericViewController<MultipleDaysCalendar> {

  protected MultipleDaysCalendar createView() {
    return new MultipleDaysCalendar() {
      @Override
      protected MultipleDaysPanel createDaysPanel() {
        return new MultipleDaysPanel() {

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
      MutableDateTime copy = date.toMutableDateTime();
      AppConfiguration cfg = AppInjector.GIN.getInjector().getConfiguration();
      //adjust to first day of week
      int firstDay = cfg.startDayOfWeek();
      while (copy.getDayOfWeek() != firstDay) {
        copy.addDays(-1);
      }
      getFactory().init(IntervalType.WEEK, copy);
    }
    Interval period = getFactory().interval();
    getDecorator().decorate(period, getViewWidget());
    return period;
  }

}
