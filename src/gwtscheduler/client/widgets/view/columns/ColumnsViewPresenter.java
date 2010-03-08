package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.common.decoration.DecorationRenderer;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.navigation.*;
import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.Period;
import org.goda.time.ReadableDateTime;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewPresenter implements CalendarPresenter, ComplexGrid {
  private DateGenerator dateGenerator;
  private List<CalendarColumn> columns;
  private CalendarTitlesRenderer titlesRenderer; 
  private EventBus eventBus;
  private Display display;
  private String tabLabel;

  public ColumnsViewPresenter(DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.titlesRenderer = titlesRenderer;
    this.eventBus = eventBus;
  }

  public ColumnsViewPresenter( List<CalendarColumn> columns,DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.columns = columns;
    this.titlesRenderer = titlesRenderer;
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

    titlesRenderer.renderVerticalTitles(interval,display.getDecorables().getRowsDecorableElements());

    eventBus.addHandler(WidgetResizeEvent.getType(),display.getMainPanel().getWidgetResizeHandler());
    eventBus.addHandler(WidgetResizeEvent.getType(),display.getCalendarHeaderResizeHandler());

    eventBus.addHandler(NavigateNextEvent.TYPE, new NavigateNextEventHandler() {
      @Override
      public void onNavigateNext() {
//        display.removeColumn();
//        eventBus.fireEvent(new WidgetResizeEvent());

        titlesRenderer.renderHorizontalTitles(columns,display.getDecorables().getColumnsDecorableElements());
//        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
//        decorationRenderer.decorateHorizontalTitlesLine(dateGenerator.next().interval(),display.getDecorables());
      }
    });

    eventBus.addHandler(NavigatePreviousEvent.TYPE, new NavigatePreviousEventHandler() {
      @Override
      public void onNavigatePrevious() {
//        titlesRenderer.renderVerticalTitles(interval,display.getDecorables().getRowsDecorableElements());
        titlesRenderer.renderHorizontalTitles(columns,display.getDecorables().getColumnsDecorableElements());
//        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
//        decorationRenderer.decorateHorizontalTitlesLine(dateGenerator.previous().interval(),display.getDecorables());
      }
    });


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
//        titlesRenderer.renderVerticalTitles(interval,display.getDecorables().getRowsDecorableElements());
        titlesRenderer.renderHorizontalTitles(columns,display.getDecorables().getColumnsDecorableElements());
//        decorationRenderer.decorateVerticalTimeLine(interval,display.getDecorables());
//        Interval interval = new Interval(date,date);
//        decorationRenderer.decorateHorizontalTitlesLine(interval,display.getDecorables());
      }
    });
  }

  @Override
  public void setColNum(int columns) {
//    this.columns = columns;
  }

  @Override
  public void setTabLabel(String tabLabel) {
    this.tabLabel = tabLabel;
  }

  @Override
  public Display getDisplay() {
    return display; 
  }

  @Override
  public String getTitle() {
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
  public void deleteColumn(CalendarColumn column) {
    for (CalendarColumn calendarColumn : columns) {
      if (calendarColumn.getTitle().equals(column.getTitle())){
        int index = columns.indexOf(calendarColumn);
        columns.remove(calendarColumn);
        display.removeColumn(index);
        titlesRenderer.renderHorizontalTitles(columns,display.getDecorables().getColumnsDecorableElements());
        eventBus.fireEvent(new WidgetResizeEvent());
      }
    }
  }

  @Override
  public void addColumn(CalendarColumn column) {
    columns.add(column);
    display.addColumn(column.getTitle());
    titlesRenderer.renderHorizontalTitles(columns,display.getDecorables().getColumnsDecorableElements());
    eventBus.fireEvent(new WidgetResizeEvent());
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
