package gwtscheduler.client.widgets.view;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.ColumnView;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.navigation.EventNavigationListener;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.common.EventsMediator;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class MultiColumnPresenter implements CalendarPresenter, EventNavigationListener, ComplexGrid {
  private int rows;
  private AppConfiguration cfg;
  private MultipleElementsIntervalDecorator decorator;
  private ColumnTitleProvider columnTitleProvider;
  private EventBus eventBus;
  private Display display;
  private int columns;
  private String tabLabel;





  /**
   * Default constructor.
   *
   * @param columnTitleProvider
   * @param eventBus the event bus
   */
  public MultiColumnPresenter(AppConfiguration cfg, @ColumnView MultipleElementsIntervalDecorator decorator, ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
    this.cfg = cfg;
    this.decorator = decorator;
    this.columnTitleProvider = columnTitleProvider;
    this.eventBus = eventBus;
    rows =  cfg.rowsInDay();

    //TODO: investigate this
//    new EventsMediator(this,eventBus);
  }

  @Override
  public void bindDispaly(Display display) {
    this.display = display;
  }

  @Override
  public void setColNum(int columns) {
    this.columns = columns;
  }

  @Override
  public void setTabLabel(String tabLabel) {
    this.tabLabel = tabLabel;
  }

  @Override
  public String getTabLabel() {
    return tabLabel;
  }

  @Override
  public EventNavigationListener getNavigationListener() {
    return this;
  }

  @Override
  public Widget getWidgetDisplay() {
    return (Widget) display;
  }

  @Override
  public void forceLayout() {
    display.forceLayout();
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end).plus(getDurationPerCells(1));
    //this is to make sure that [0,0] is at least one cell's duration
    return new Interval(from, to);
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    return null;
  }

  @Override
  public int getRowNum() {
    return 0;
  }

  @Override
  public int getColNum() {
    return 0;
  }

  @Override
  public int getWidth() {
    return 0;
  }

  @Override
  public int getHeight() {
    return 0;
  }

  @Override
  public Interval onNavigateNext() {
    return null;
  }

  @Override
  public Interval onNavigatePrevious() {
    return null;
  }

  @Override
  public Interval onNavigateTo(ReadableDateTime date) {
    return null;
  }

  @Override
  public Interval getCurrentInterval() {
    return null;
  }
//  private AppConfiguration cfg;
//  private ColumnTitleProvider columnTitleProvider;
////  private AbstractDaysView view;
//  private MultipleElementsIntervalDecorator decorator;
//  private EventBus eventBus;
//
//  /**
//   * holds the number of rows within a day
//   */
//  private final int rows;
////  private Display display;
//  private String tabLabel;
//  private int columns;
//
//  /**
//   * Default constructor.
//   *
//   * @param columnTitleProvider
//   * @param eventBus the event bus
//   */
//  public MultiColumnPresenter(AppConfiguration cfg, @ColumnView MultipleElementsIntervalDecorator decorator, ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
//    super(eventBus);
//    this.cfg = cfg;
//    this.columnTitleProvider = columnTitleProvider;
////    this.view = view;
//    this.decorator = decorator;
//    this.eventBus = eventBus;
//    rows =  cfg.rowsInDay();
//  }
//
//  @Override
//  public void bindDispaly(Display display) {
//    this.display = display;
//  }
//
//  @Override
//  public AbstractDaysView getDisplay() {
//    return display;
//  }
//
//  public String getTabLabel() {
//    return tabLabel;
//  }
//
//  public void setTabLabel(String tabLabel) {
//    this.tabLabel = tabLabel;
//  }
//
//  @Override
//  public Instant getInstantForCell(int[] start) {
//    return null;
//  }
//
//  @Override
//  protected Duration getDurationPerCells(int count) {
//    return null;
//  }
//
//  public int getRowNum() {
//    return rows;
//  }
//
//  public int getColNum() {
//    return columns;
//  }
//
//  public void setColNum(int columns) {
//    this.columns = columns;
//  }
//
//  public Interval onNavigateNext() {
//    return null;
//  }
//
//  public Interval onNavigatePrevious() {
//    return null;
//  }
//
//  public Interval onNavigateTo(ReadableDateTime date) {

//    return null;

//  }
}
