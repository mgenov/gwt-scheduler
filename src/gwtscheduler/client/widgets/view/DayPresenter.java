package gwtscheduler.client.widgets.view;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.TicketPresenter;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import gwtscheduler.common.calendar.IntervalType;
import net.customware.gwt.presenter.client.EventBus;

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
  private AbstractDaysView view;

  /**
   * Default constructor.
   * @param cfg the application configuration
   */
  @Inject
  protected DayPresenter(AppConfiguration cfg, @Day AbstractDaysView view, EventBus bus) {
    super(view, bus);
    this.view = view;
    rows = cfg.rowsInDay();
    getDisplay().initLasso(new VerticalLassoStrategy(false), this);
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
    return rows;
  }

  public Interval onNavigateNext() {
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

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = getCurrentInterval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.add(getDurationPerCells(distance));
    return time.toInstant();
  }

  @Override
  public void onDropEvent(DropEvent event) {
//    int x = event.getMouseX();
//    int y = event.getMouseY();
//
//    int colPos = (x / (this.getWidth() / this.getColNum()));
//    int rowPos = (y / (this.getHeight() / this.getRowNum()));
//
//    int rowH = Math.round((float) this.getHeight() / this.getRowNum());
//    int colW = Math.round((float) this.getWidth() / this.getColNum());
//    int[] position = new int[] {colPos * colW, rowPos * rowH};
//
//    TicketPresenter presenter = (TicketPresenter)event.getDroppedObject();
//    Widget w = event.getSourceWidget();
//
////    eventsPanel.setComplexGrid(grid);
//
//    int h = eventPosition[2];
//    int we = eventPosition[3];
//    w.setPixelSize(h, we * presenter.getDuration());
//    w.getElement().getStyle().setZIndex(20);
//    eventsPanel.add(w, eventPosition[0], eventPosition[1]);
//    view.onDropEvent(event, this);
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
