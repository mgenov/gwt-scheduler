package gwtscheduler.client.widgets.view;

import gwtscheduler.client.interfaces.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysCalendar;
import gwtscheduler.common.calendar.IntervalType;
import net.customware.gwt.presenter.client.EventBus;

import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayPresenter extends
    AbstractCalendarPresenter<AbstractDaysCalendar> {

  /** number of rows */
  public final int Rows;

  @Day
  @Inject
  protected MultipleElementsIntervalDecorator decorator;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayPresenter(AppConfiguration cfg, @Day AbstractDaysCalendar view,
      EventBus bus) {
    super(view, bus);
    Rows = cfg.rowsInDay();
  }

  public String getTabLabel() {
    return "Day";
  }

  @Override
  public int getColNum() {
    return 1;
  }

  @Override
  public int getRowNum() {
    return Rows;
  }

  public Interval onNavigateNext() {
    //TODO verify that the view is attached
    Interval tp = getFactory().next().interval();
    decorator.decorate(tp, getDisplay().getDecorables());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    decorator.decorate(period, getDisplay().getDecorables());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    decorator.decorate(period, getDisplay().getDecorables());
    return period;
  }

}
