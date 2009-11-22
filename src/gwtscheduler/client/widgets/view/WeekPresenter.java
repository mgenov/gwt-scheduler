package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.view.common.GenericViewController;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysCalendar;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Week controller for week views.
 * @author malp
 */
@Singleton
public class WeekPresenter extends GenericViewController<AbstractDaysCalendar> {

  private final int Rows;
  //XXX: correct this
  public static final int ROWS = AppInjector.GIN.getInjector().getConfiguration().rowsInDay();

  @Inject
  @Week
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected WeekPresenter(AppConfiguration cfg, @Week AbstractDaysCalendar view) {
    this.Rows = cfg.rowsInDay();
    this.view = view;
  }

  public String getTabLabel() {
    return "Week";
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getViewWidget());
    return tp;
  }

  public Interval onNavigatePrevious() {
    //TODO verify that the view is attached
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getViewWidget());
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
    decorator.decorate(period, getViewWidget());
    return period;
  }

}
