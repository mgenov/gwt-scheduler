package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.TicketPresenter;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.navigation.*;
import org.goda.time.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewPresenter implements CalendarPresenter, ComplexGrid {
  private DateGenerator dateGenerator;
  private List<CalendarColumn> columns;
  private CalendarTitlesRenderer titlesRenderer;
  private CalendarHeader calendarHeader;
  private CalendarContent calendarContent;
  private EventBus eventBus;
  private Display display;
  private String tabLabel;
  private ArrayList<CalendarDropHandler> handler = new ArrayList<CalendarDropHandler>();

  public ColumnsViewPresenter(DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.titlesRenderer = titlesRenderer;
    this.eventBus = eventBus;
  }

  public ColumnsViewPresenter( List<CalendarColumn> columns,DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer,CalendarHeader calendarHeader,CalendarContent calendarContent, EventBus eventBus) {
    this.dateGenerator = dateGenerator;
    this.columns = columns;
    this.titlesRenderer = titlesRenderer;
    this.calendarHeader = calendarHeader;
    this.calendarContent = calendarContent;
    this.eventBus = eventBus;
  }

  @Override
  public void bindDisplay(final Display display) {
    this.display = display;
    calendarHeader.bindDisplay(display.getCalendarHeaderDisplay());
    calendarContent.bindDisplay(display.getCalendarContentDisplay());

    display.initLasso(new VerticalLassoStrategy(false), this);
    final Interval interval = dateGenerator.interval();

    titlesRenderer.renderVerticalTitles(interval, calendarContent.getFrameGridDecorables());


    eventBus.addHandler(NavigateNextEvent.TYPE, new NavigateNextEventHandler() {
      @Override
      public void onNavigateNext() {
        titlesRenderer.renderHorizontalTitles(columns,calendarHeader.getHeaderDecorableElements());
      }
    });

    eventBus.addHandler(NavigatePreviousEvent.TYPE, new NavigatePreviousEventHandler() {
      @Override
      public void onNavigatePrevious() {
        titlesRenderer.renderHorizontalTitles(columns,calendarHeader.getHeaderDecorableElements());
      }
    });


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
        titlesRenderer.renderHorizontalTitles(columns,calendarHeader.getHeaderDecorableElements());
      }
    });

    display.addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        proceedDropEvent(event);
      }
    });
  }

  private void proceedDropEvent(DropEvent event){
    String columnTitle = null;
    Instant time = null;
    String oldColumnTitle = null;
    Instant oldTime = null;
    Object o = event.getDroppedObject();

    int[] oldCell = calendarContent.getCellPosition(event.getStartX(), event.getStartY());
    if(oldCell[0] >= 0 && oldCell[1] >= 0){
      oldColumnTitle = columns.get(oldCell[1]).getTitle();
      oldTime = getInstantForCell(oldCell);
    }
    int[] cell = calendarContent.getCellPosition(event.getEndX(), event.getEndY());
    columnTitle = columns.get(cell[1]).getTitle();
    time = getInstantForCell(cell);

    CalendarDropEvent calendarDrop = new CalendarDropEvent(columnTitle, time, oldColumnTitle, oldTime, o);
    mediateEvent(calendarDrop);

    calendarContent.attachWidget(event.getSourceWidget(), cell);
    getRowForInstant(time);
  }

  public int getRowForInstant(Instant time) {
    int minutesPerCell = (24 * 60) / getRowNum();
    MutableDateTime mTime = time.toMutableDateTime();
    int cell = Math.round(mTime.getMinuteOfDay()/minutesPerCell);
    GWT.log("MutableDateTime : " + cell, null);
    return cell;
  }

  private void mediateEvent(CalendarDropEvent calendarDrop){
    for(CalendarDropHandler handler : this.handler){
      handler.onCalendarDrop(calendarDrop);
    }
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
    int distance = (start[1] * getRowNum()) + start[0];
    ReadableInterval curr = dateGenerator.interval().toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();
    time.add(getDurationPerCells(distance));
    return time.toInstant();
  }

  @Override
  public void deleteColumn(CalendarColumn column) {
    for (CalendarColumn calendarColumn : columns) {
      if (calendarColumn.getTitle().equals(column.getTitle())){
        int index = columns.indexOf(calendarColumn);

        columns.remove(index);
        calendarContent.removeColumn(index);
        fireResizeRedrawEvents();
        calendarHeader.removeColumnHeader(index);

        titlesRenderer.renderHorizontalTitles(columns,calendarHeader.getHeaderDecorableElements());

        return;
      }
    }
  }

  private void fireResizeRedrawEvents() {
    calendarContent.fireResizeRedrawEvents();
  }

  @Override
  public void addColumn(CalendarColumn column) {
    columns.add(column);
    calendarContent.addColumn(column.getTitle());
    fireResizeRedrawEvents();
    calendarHeader.addColumnHeader(column.getTitle());
    titlesRenderer.renderHorizontalTitles(columns,calendarHeader.getHeaderDecorableElements());
  }

  @Override
  public void addCalendarDropHandler(CalendarDropHandler handler) {
    this.handler.add(handler);
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
