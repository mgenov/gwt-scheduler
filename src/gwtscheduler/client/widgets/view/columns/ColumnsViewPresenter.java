package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEvent;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.common.events.CellDropEvent;
import gwtscheduler.client.widgets.view.common.events.CellDropHandler;
import gwtscheduler.client.widgets.view.common.events.MoveObjectEvent;
import gwtscheduler.client.widgets.view.common.events.MoveObjectHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.CalendarEventDeleteEvent;
import gwtscheduler.client.widgets.view.event.CalendarEventDeleteEventHandler;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickEvent;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

import java.util.List;

/**
 * Represents a calendar
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnsViewPresenter implements CalendarPresenter, ComplexGrid {
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
  public ColumnsViewPresenter(CalendarColumnsProvider columnsProvider, DateGenerator dateGenerator, CalendarTitlesRenderer titlesRenderer, CalendarHeader calendarHeader, CalendarContent calendarContent, EventBus eventBus) {
    this.columnsProvider = columnsProvider;
    this.dateGenerator = dateGenerator;
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

//    display.initLasso(new VerticalLassoStrategy(false), this);
    
    final Period interval = dateGenerator.interval();

    titlesRenderer.renderVerticalTitles(interval, calendarContent.getFrameGridDecorables());

    eventBus.addHandler(CalendarEventDeleteEvent.TYPE,new CalendarEventDeleteEventHandler(){
      @Override
      public void onEventDelete(CalendarEventDeleteEvent e) {
           eventBus.fireEvent(new EventDeleteEvent(e.getEvent()));
      }
    });

    eventBus.addHandler(MoveObjectEvent.TYPE, new MoveObjectHandler(){
      @Override
      public void onMoveObject(MoveObjectEvent event) {
        CalendarColumn oldColumn = columns.get(event.getOldCell()[1]); // you can move this 2 lines of code in EventsDashboard if you need to move columns list.
        CalendarColumn newColumn = columns.get(event.getNewCell()[1]);

        CalendarObjectMoveEvent objectMoveEvent = new CalendarObjectMoveEvent(type, title, event.getDroppedObject(), oldColumn, event.getOldTime(), newColumn, event.getNewTime());
        eventBus.fireEvent(objectMoveEvent);
      }
    });

    eventBus.addHandler(CellDropEvent.TYPE, new CellDropHandler(){
      @Override
      public void onCellDrop(CellDropEvent event) {
        CalendarColumn column = columns.get(event.getCell()[1]);  // you can move this line of code in EventsDashboard if you need to move columns list.

        CalendarDropEvent dropEvent = new CalendarDropEvent(type, title, event.getDroppedObject(), column, event.getTime());
        eventBus.fireEvent(dropEvent);
      }
    });
  }

  private void reRenderHeaderTitles(Period interval) {
//    DurationInterval durationInterval = DurationInterval.getInterval(interval.getStartMillis(),interval.getEndMillis());
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

  /**
   * Remove given column from calendar.
   *
   * @param column to be removed.
   */
  @Override
  public void removeColumn(CalendarColumn column) {
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
   * Adds a new Column to the calendar.
   * 
   * @param column to be added.
   */
  @Override
  public void addColumn(CalendarColumn column) {
    columns.add(column);
    calendarContent.addColumn(column.getTitle());
    fireResizeRedrawEvents();
    calendarHeader.addColumnHeader(column.getTitle());
    titlesRenderer.renderHorizontalTitles(columns, calendarHeader.getHeaderDecorableElements());
  }

  /**
   * Add handler who will catch CalendarDropEvent. This event is fired when something is dropped over calendar.
   *
   * @param handler who will handle event.
   * @return object that is responsible for removing handler from handler manager.
   */
  @Override
  public HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler) {
    return eventBus.addHandler(CalendarDropEvent.TYPE, handler);
  }

  /**
   * Add handler who will catch CalendarObjectMoveEvent. This event is fired when something is moved from one place to
   * another over the calendar.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  @Override
  public HandlerRegistration addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    return eventBus.addHandler(CalendarObjectMoveEvent.TYPE, handler);
  }

  /**
   * Sets calendar type.
   *
   * @param type CalendarType.
   */
  @Override
  public void setCalendarType(CalendarType type) {
    this.type = type;
  }

  /**
   * Return calendar type.
   * 
   * @return CalendarType.
   */
  @Override
  public CalendarType getCalendarType() {
    return type;
  }

  /**
   * Add new event on the calendar. This event will be wrapped with CalendarEvent and will be placed on the calendar.
   * 
   * @param event to be added to the calendar.
   */
  @Override
  public void addCalendarEvent(Event event) {
    calendarContent.addCalendarEvent(event);
  }

  /**
   * Add handler for CalendarEventDurationChangeEvent. This event is fired when height of the CalendarEvent is changed.
   * This means when mouse is up. When resizing is finished.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  @Override
  public HandlerRegistration addEventDurationChangeHandler(CalendarEventDurationChangeHandler handler) {
    return eventBus.addHandler(CalendarEventDurationChangeEvent.TYPE, handler);
  }

  /**
   * Add handler for CalendarEventDurationChangeStartEvent. This event is fired when someone click on CalendarEvent to
   * resize the event. This mean not resizing in the real time, just click on resize button.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  @Override
  public HandlerRegistration addEventDurationChangeStartHandler(CalendarEventDurationChangeStartHandler handler) {
    return eventBus.addHandler(CalendarEventDurationChangeStartEvent.TYPE, handler);
  }

  /**
   * Add handler for EventDeleteEvent. This event is fired when someone close the event.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  @Override
  public HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler) {
    return eventBus.addHandler(EventDeleteEvent.TYPE, handler);
  }

  /**
   * Delete event from calendar.
   *
   * @param event to be deleted.
   */
  @Override
  public void deleteEvent(Event event) {
    calendarContent.deleteEvent(event);
  }

  /**
   * Update current event on the calendar.
   *
   * @param event event to be updated with new data that is carried with given event.
   */
  @Override
  public void updateEvent(Event event) {
    calendarContent.updateEvent(event);
  }

  @Override
  public void navigateToDateTime(DateTime date) {
    Period interval = dateGenerator.getIntervalForDate(date);
    reRenderHeaderTitles(interval);
    eventBus.fireEvent(new NavigateToEvent(date));
  }

  @Override
  public void setEnable(boolean enable) {
    calendarContent.setEnable(enable);
  }

  /**
   * Add handler for event that is fired when some one click on the CalendarEvent.
   * 
   * @param handler who will handle the event.
   */
  @Override
  public void addEventClickHandler(EventClickHandler handler) {
    eventBus.addHandler(EventClickEvent.TYPE, handler);
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
