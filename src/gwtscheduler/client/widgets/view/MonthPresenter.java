package gwtscheduler.client.widgets.view;

import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.HorizontalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.MonthTitleProvider;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.month.MonthDisplay;
import gwtscheduler.common.calendar.IntervalType;
import org.goda.time.Days;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.Period;
import org.goda.time.PeriodType;
import org.goda.time.ReadableDateTime;
import org.goda.time.ReadableInterval;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Controller class for months.
 * @author malp
 */
@Singleton
public class MonthPresenter extends AbstractCalendarPresenter<MonthDisplay> implements ComplexGrid {

  /** defines the number of days in a week */
  private final int WeekSize;

  @Inject
  @Month
  private MultipleElementsIntervalDecorator decorator;
  private MonthDisplay display;
  private MonthTitleProvider columnTitleProvider;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  public MonthPresenter(AppConfiguration cfg, @Month MonthDisplay display, MonthTitleProvider columnTitleProvider, EventBus bus) {
    super(bus);
    this.display = display;
    this.columnTitleProvider = columnTitleProvider;
    WeekSize = cfg.daysInWeek();
    getDisplay().initLasso(new HorizontalLassoStrategy(), this);
  }

  public String getTabLabel() {
    return "Month";
  }

  @Override
  public void bindDisplay(Display display) {
  }

  @Override
  public void setColNum(int columns) {

  }

  @Override
  public void setTabLabel(String tabLabel) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public MonthDisplay getDisplay() {
    return display;
  }

  public Interval onNavigateNext() {
    Interval next = getFactory().next().interval();
    adjustVisibleRows(next);
    columnTitleProvider.setInterval(next);
    decorator.decorate(next,columnTitleProvider, getDisplay().getDecorables());
    return next;
  }

  public Interval onNavigatePrevious() {
    Interval prev = getFactory().previous().interval();
    adjustVisibleRows(prev);
    columnTitleProvider.setInterval(prev);
    decorator.decorate(prev,columnTitleProvider, getDisplay().getDecorables());
    return prev;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.MONTH, date);
    }
    Interval intv = getFactory().interval();
    adjustVisibleRows(intv);
    Interval interval = new Interval(intv.getStart(),new Period(0, 0, 0, WeekSize, 0, 0, 0, 0));
    columnTitleProvider.setInterval(interval);
    decorator.decorate(intv,columnTitleProvider, getDisplay().getDecorables());
    return intv;
  }

  @Override
  public int getColNum() {
    return WeekSize;
  }

  @Override
  public int getRowNum() {
    return getDisplay().getVisibleRows();
  }

  /**
   * Adjusts the number of visible rows, according to the weeks.
   * @param intv the interval that is to show
   */
  protected void adjustVisibleRows(Interval intv) {
    //must show the necessary rows only
    int weeks = (Days.daysIn(intv).getDays() + 1) / WeekSize;
    getDisplay().showRows(weeks);
  }

  @Override
  protected Duration getDurationPerCells(int count) {
    return new Period(count, PeriodType.days()).toStandardDuration();
  }

  /**
   * LassoSubject impl.
   */

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.addDays(distance);
    return time.toInstant();
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

}