package gwtscheduler.client.widgets.view;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.annotation.ColumnView;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.decoration.DecorationRenderer;
import gwtscheduler.client.widgets.common.event.WidgetRedrawEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.navigation.*;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.Period;
import org.goda.time.ReadableDateTime;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class MultiColumnPresenter implements CalendarPresenter, ComplexGrid {
  private int rows;
//  private AppConfiguration cfg;
//  private MultipleElementsIntervalDecorator decorator;
//  private ColumnTitleProvider columnTitleProvider;
  private DateGenerator dateGenerator;
  private DecorationRenderer decorationRenderer;
  private EventBus eventBus;
  private Display display;
  private int columns;
  private String tabLabel;

  public MultiColumnPresenter(AppConfiguration cfg, DateGenerator dateGenerator, DecorationRenderer decorationRenderer, EventBus eventBus) {
//    this.cfg = cfg;
    this.dateGenerator = dateGenerator;
    this.decorationRenderer = decorationRenderer;
    this.eventBus = eventBus;
  }

//  /**
//   * Default constructor.
//   *
//   * @param columnTitleProvider
//   * @param eventBus            the event bus
//   */
//  public MultiColumnPresenter(AppConfiguration cfg, @ColumnView MultipleElementsIntervalDecorator decorator, ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
//    this.cfg = cfg;
//    this.decorator = decorator;
//    this.columnTitleProvider = columnTitleProvider;
//    this.eventBus = eventBus;
//    rows = cfg.rowsInDay();
//
//    //TODO: investigate this
////    new EventsMediator(this,eventBus);
//  }

  @Override
  public void bindDisplay(final Display display) {
    this.display = display;
    display.initLasso(new VerticalLassoStrategy(false), this);
    final Interval interval = dateGenerator.interval();

    eventBus.addHandler(WidgetResizeEvent.getType(),display.getMainPanel().getWidgetResizeHandler());

    eventBus.addHandler(NavigateNextEvent.TYPE, new NavigateNextEventHandler() {
      @Override
      public void onNavigateNext() {
        display.removeColumn();
        eventBus.fireEvent(new WidgetResizeEvent());
        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
        decorationRenderer.decorateHorizontalTitlesLine(dateGenerator.next().interval(),display.getDecorables());

      }
    });

    eventBus.addHandler(NavigatePreviousEvent.TYPE, new NavigatePreviousEventHandler() {
      @Override
      public void onNavigatePrevious() {
        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
        decorationRenderer.decorateHorizontalTitlesLine(dateGenerator.previous().interval(),display.getDecorables());
      }
    });


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
        Interval interval = new Interval(date,date);
        decorationRenderer.decorateHorizontalTitlesLine(interval,display.getDecorables());
      }
    });
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
    return null;
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


  protected Duration getDurationPerCells(int count) {
    int minutesPerCell = (24 * 60) / getRowNum();
    return new Period(0,minutesPerCell * count, 0,0).toStandardDuration();
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    return null;
  }

  @Override
  public int getRowNum() {
    return display.getRowNum();
  }

  @Override
  public int getColNum() {
    return display.getColNum();
  }

  @Override
  public int getWidth() {
    return display.getWidth();
  }

  @Override
  public int getHeight() {
    return display.getHeight();
  }
}
