package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import gwtscheduler.common.calendar.IntervalType;
import net.customware.gwt.presenter.client.EventBus;

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
public class WeekPresenter extends AbstractCalendarPresenter<AbstractDaysView> {

  /** holds the number of rows within a day */
  private final int rows;

  @Inject
  @Week
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected WeekPresenter(AppConfiguration cfg, @Week AbstractDaysView view,
      EventBus bus) {
    super(view, bus);
    rows = cfg.rowsInDay();
    display.asLassoPanel().initLasso(new VerticalLassoStrategy(false), this);
  }

  public String getTabLabel() {
    return "Week";
  }

  @Override
  public int getColNum() {
    return 7;

  }

  @Override
  public int getRowNum() {
    return rows;
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getDisplay().getDecorables());
    return tp;
  }

  public Interval onNavigatePrevious() {
    //TODO verify that the view is attached
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getDisplay().getDecorables());
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
    decorator.decorate(period, getDisplay().getDecorables());
    return period;
  }

}
