package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.DaysTitleProvider;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.Period;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

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
  private DaysTitleProvider columnTitleProvider;
  private int columns;
  private String tabLabel;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected WeekPresenter(AppConfiguration cfg, @Week AbstractDaysView display, DaysTitleProvider columnTitleProvider, EventBus bus) {
    super(bus);
    this.display = display;
    this.columnTitleProvider = columnTitleProvider;
    rows = cfg.rowsInDay();
    getDisplay().initLasso(new VerticalLassoStrategy(false), this);
  }

  @Override
  public AbstractDaysView getDisplay() {
    return display;
  }

  @Override
  public void bindDisplay(Display display) {
  }

  @Override
  public void setColNum(int columns) {
    this.columns = columns;
  }
  //TODO: must be used in the builder to set the tab label
  @Override
  public void setTabLabel(String tabLabel) {
    this.tabLabel = tabLabel;
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
    columnTitleProvider.setInterval(tp);
    decorator.decorate(tp, columnTitleProvider, getDisplay().getDecorables());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    columnTitleProvider.setInterval(period);
    decorator.decorate(period, columnTitleProvider, getDisplay().getDecorables());
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
    columnTitleProvider.setInterval(period);
    decorator.decorate(period, columnTitleProvider, getDisplay().getDecorables());
    return period;
  }

  public Instant getInstantForCell(int[] start) {
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    int minutesPerCell = (24 * 60) / getRowNum();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.addMinutes(minutesPerCell * distance);
    return time.toInstant();
  }

  @Override
  protected Duration getDurationPerCells(int count) {
    int minutesPerCell = (24 * 60) / getRowNum();
    return new Period(0,minutesPerCell * count, 0,0).toStandardDuration();
  }
}
