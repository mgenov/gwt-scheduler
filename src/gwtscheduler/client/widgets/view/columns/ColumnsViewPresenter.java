package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMovetEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEvent;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.common.DropObjectEvent;
import gwtscheduler.client.widgets.view.common.DropObjectHandler;
import gwtscheduler.client.widgets.view.common.MoveObjectEvent;
import gwtscheduler.client.widgets.view.common.MoveObjectHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEndEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEndHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.CalendarEventDeleteEvent;
import gwtscheduler.common.event.CalendarEventDeleteEventHandler;
import gwtscheduler.common.event.Event;
import gwtscheduler.client.widgets.common.navigation.*;
import org.goda.time.DateTime;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.ReadableDateTime;

import java.util.List;

/**
 * Represents a calendar
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewPresenter implements CalendarPresenter, ComplexGrid {
  private final EventBus calendarBus;
  private final CalendarColumnsProvider columnsProvider;
  private final DateGenerator dateGenerator;
  private final CalendarTitlesRenderer titlesRenderer;
  private final CalendarHeader calendarHeader;
  private final CalendarContent calendarContent;
  private final EventBus eventBus;
  private List<CalendarColumn> columns;
  private Display display;
  private String title;
  private CalendarType type;

  /**
   * Default constructor
   *
   * @param columnsProvider
   * @param dateGenerator
   * @param titlesRenderer
   * @param calendarHeader
   * @param calendarContent
   * @param eventBus
   */
  public ColumnsViewPresenter(CalendarColumnsProvider columnsProvider, DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer, CalendarHeader calendarHeader, CalendarContent calendarContent, EventBus eventBus, EventBus calendarBus) {
    this.columnsProvider = columnsProvider;
    this.dateGenerator = dateGenerator;
    this.calendarBus = calendarBus;
    this.columns = columnsProvider.getColumns();
    this.titlesRenderer = titlesRenderer;
    this.calendarHeader = calendarHeader;
    this.calendarContent = calendarContent;
    this.eventBus = eventBus;
  }

  /**
   * Binds the display to the presenter
   *
   * @param display
   */
  @Override
  public void bindDisplay(final Display display) {
    this.display = display;
    calendarHeader.bindDisplay(display.getCalendarHeaderDisplay());
    calendarContent.bindDisplay(display.getCalendarContentDisplay());
    calendarContent.setColumns(columns);

    //adds  WidgetResizeHandler
    display.addWidgetResizeHandler(calendarContent.getEventsDachboardWidgetResizeHandler());

    display.initLasso(new VerticalLassoStrategy(false), this);
    final Interval interval = dateGenerator.interval();

    titlesRenderer.renderVerticalTitles(interval, calendarContent.getFrameGridDecorables());


    eventBus.addHandler(NavigateNextEvent.TYPE, new NavigateNextEventHandler() {
      @Override
      public void onNavigateNext() {
        reRenderHeaderTitles(dateGenerator.next().interval());
      }
    });

    eventBus.addHandler(NavigatePreviousEvent.TYPE, new NavigatePreviousEventHandler() {
      @Override
      public void onNavigatePrevious() {
        reRenderHeaderTitles(dateGenerator.previous().interval());
      }
    });


    eventBus.addHandler(NavigateToEvent.TYPE, new NavigateToEventHandler() {
      @Override
      public void onNavigateTo(ReadableDateTime date) {
        reRenderHeaderTitles(dateGenerator.getIntervalForDate((DateTime) date));
      }
    });


    eventBus.addHandler(CalendarEventDeleteEvent.TYPE,new CalendarEventDeleteEventHandler(){
      @Override
      public void onEventDelete(CalendarEventDeleteEvent e) {
           calendarBus.fireEvent(new EventDeleteEvent(e.getEvent()));
      }
    });

    calendarBus.addHandler(MoveObjectEvent.TYPE, new MoveObjectHandler(){
      @Override
      public void onMoveObject(MoveObjectEvent event) {
        CalendarColumn oldColumn = columns.get(event.getOldCell()[1]); // you can move this 2 lines of code in EventsDashboard if you need to move columns list.
        CalendarColumn newColumn = columns.get(event.getNewCell()[1]);

        CalendarObjectMovetEvent objectMovetEvent = new CalendarObjectMovetEvent(type, title, event.getDroppedObject(), oldColumn, event.getOldTime(), newColumn, event.getNewTime());
        calendarBus.fireEvent(objectMovetEvent);
      }
    });

    calendarBus.addHandler(DropObjectEvent.TYPE, new DropObjectHandler(){
      @Override
      public void onDropObject(DropObjectEvent event) {
        CalendarColumn column = columns.get(event.getNewCell()[1]);  // you can move this line of code in EventsDashboard if you need to move columns list.

        CalendarDropEvent dropEvent = new CalendarDropEvent(type, title, event.getDroppedObject(), column, event.getNewTime());
        calendarBus.fireEvent(dropEvent);
      }
    });
  }

  private void reRenderHeaderTitles(Interval interval) {
    columnsProvider.updateColumns(interval, columns);
    titlesRenderer.renderHorizontalTitles(columns, calendarHeader.getHeaderDecorableElements());
  }

  /**
   * Sets the Calendar title
   *
   * @param title
   */
  @Override
  public void setTittle(String title) {
    this.title = title;
  }

  @Override
  public Display getDisplay() {
    return display;
  }

  @Override
  public String getTitle() {
    return title;
  }


  @Override
  public void forceLayout() {
    display.forceLayout();
  }

//  //TODO:remove this, used only in eventsmediator
//  @Override
//  public Interval getIntervalForRange(int[] start, int[] end) {
//    Interval interval = dateGenerator.getIntervalForRange(start,end,getRowNum());
//    return interval;
//  }


  /**
   * Deletes a column from the calendar if it exists
   *
   * @param column
   */
  @Override
  public void deleteColumn(CalendarColumn column) {
    for (CalendarColumn calendarColumn : columns) {
      if (calendarColumn.getId().equals(column.getId())) {
        int index = columns.indexOf(calendarColumn);

        columns.remove(index);
        calendarContent.removeColumn(calendarColumn,index);
        fireResizeRedrawEvents();
        calendarHeader.removeColumnHeader(index);

        titlesRenderer.renderHorizontalTitles(columns, calendarHeader.getHeaderDecorableElements());

        return;
      }
    }
  }

  /**
   * fires an events for resizing the columns and the header. It is need in case we remove or add column.
   * the columns size must be optimized in order to use the full space of the screen
   */
  private void fireResizeRedrawEvents() {
    calendarContent.fireResizeRedrawEvents();
  }

  /**
   * Adds a new Column in the calenadar
   * @param column
   */
  @Override
  public void addColumn(CalendarColumn column) {
    columns.add(column);
    calendarContent.addColumn(column.getTitle());
    fireResizeRedrawEvents();
    calendarHeader.addColumnHeader(column.getTitle());
    titlesRenderer.renderHorizontalTitles(columns, calendarHeader.getHeaderDecorableElements());
  }

  @Override
  public HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler) {
    return calendarBus.addHandler(CalendarDropEvent.TYPE, handler);
  }

  @Override
  public HandlerRegistration addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    return calendarBus.addHandler(CalendarObjectMovetEvent.TYPE, handler);
  }

  @Override
  public void setCalendarType(CalendarType type) {
    this.type = type;
  }

  @Override
  public CalendarType getCalendarType() {
    return type;
  }

  @Override
  public void addCalendarEvent(Event event) {
    calendarContent.addCalendarEvent(event);
  }

  @Override
  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
    return calendarBus.addHandler(CalendarEventResizeEndEvent.TYPE, handler);
  }

  @Override
  public HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    return calendarBus.addHandler(CalendarEventResizeStartEvent.TYPE, handler);
  }

  @Override
  public HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler) {
    return calendarBus.addHandler(EventDeleteEvent.TYPE, handler);
  }

  @Override
  public void deleteEvent(Event event) {
    calendarContent.deleteEvent(event);
  }
  
  @Override
  public void updateEvent(Event event) {
    calendarContent.updateEvent(event);
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
