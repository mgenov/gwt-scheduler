package gwtscheduler.client.widgets.view;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.DaysTitleProvider;
import gwtscheduler.client.widgets.view.columns.CalendarDropHandler;
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
 * Controller for days view.
 * @author malp
 */
@Singleton
public class DayPresenter extends AbstractCalendarPresenter<AbstractDaysView> {

  /** number of rows */
  public final int rows;

  @Day
  @Inject
  protected MultipleElementsIntervalDecorator decorator;
  private DaysTitleProvider columnTitleProvider;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayPresenter(AppConfiguration cfg, @Day AbstractDaysView view, DaysTitleProvider columnTitleProvider, EventBus bus) {
    super(bus);
//    this.display = view;
    this.columnTitleProvider = columnTitleProvider;
    rows = cfg.rowsInDay();
    getDisplay().initLasso(new VerticalLassoStrategy(false), this);
  }

   @Override
  public Display getDisplay(){
    return null;
  }
//  @Override
//  public AbstractDaysView getDisplay() {
//    return display;
//  }

  @Override
  public void bindDisplay(Display display) {
  }

  @Override
  public void setColNum(int columns) {
  }

  @Override
  public void setTabLabel(String tabLabel) {
  }

  public String getTitle() {
    return "Day";
  }

  @Override
  public int getColNum() {
    return 1;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  public Interval onNavigateNext() {
    Interval tp = getFactory().next().interval();
    columnTitleProvider.setInterval(tp);
//    decorator.decorate(tp, columnTitleProvider, getDisplay().getDecorables());
    return tp;
  }

  public Interval onNavigatePrevious() {
    Interval period = getFactory().previous().interval();
    columnTitleProvider.setInterval(period);
//    decorator.decorate(period, columnTitleProvider, getDisplay().getDecorables());
    return period;
  }

  public Interval onNavigateTo(ReadableDateTime date) {
    if (!date.equals(getFactory().current())) {
      getFactory().init(IntervalType.DAY, date);
    }
    Interval period = getFactory().interval();
    columnTitleProvider.setInterval(period);
//    decorator.decorate(period, columnTitleProvider, getDisplay().getDecorables());
    return period;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.add(getDurationPerCells(distance));
    return time.toInstant();
  }

  @Override
  public void addCalendarDropHandler(CalendarDropHandler handler) {
  }

  @Override
  protected Duration getDurationPerCells(int count) {
    int minutesPerCell = (24 * 60) / getRowNum();
    return new Period(0,minutesPerCell * count, 0,0).toStandardDuration();
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
