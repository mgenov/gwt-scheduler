package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarChangeEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarChangeHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.ComplexGrid;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
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
  private CalendarColumnsProvider columnsProvider;
  private DateGenerator dateGenerator;
  private List<CalendarColumn> columns;
  private CalendarTitlesRenderer titlesRenderer;
  private CalendarHeader calendarHeader;
  private CalendarContent calendarContent;
  private EventBus eventBus;
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


//    //TODO: investigate this
////    new EventsMediator(this,eventBus);

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

    calendarContent.addContentChangeCallback(new ContentChange(){

      @Override
      public void onDrop(int[] newCell, Object droppedObject) {
        Instant time = dateGenerator.getInstantForCell(newCell, getRowNum());
        CalendarColumn column = columns.get(newCell[1]);

        CalendarDropEvent event = new CalendarDropEvent(type, title, droppedObject, column, time);
        display.getHasCalendarDropHandlers().fireEvent(event);
      }

      @Override
      public void onMove(int[] oldCell, int[] newCell, Object droppedObject) {
        Instant oldTime = dateGenerator.getInstantForCell(oldCell, getRowNum());
        Instant newTime = dateGenerator.getInstantForCell(newCell,getRowNum());
        
        CalendarColumn oldColumn = columns.get(oldCell[1]);
        CalendarColumn newColumn = columns.get(newCell[1]);

        CalendarChangeEvent event = new CalendarChangeEvent(type, title, droppedObject, oldColumn, oldTime, newColumn, newTime);
        display.getHasCalendarChangeHandlers().fireEvent(event);
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

  //TODO:remove this, used only in eventsmediator
  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Interval interval = dateGenerator.getIntervalForRange(start,end,getRowNum());
    return interval;
  }


  /**
   * Deletes a column from the calendar if it exists
   *
   * @param column
   */
  @Override
  public void deleteColumn(CalendarColumn column) {
    for (CalendarColumn calendarColumn : columns) {
      if (calendarColumn.getTitle().equals(column.getTitle())) {
        int index = columns.indexOf(calendarColumn);

        columns.remove(index);
        calendarContent.removeColumn(index);
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
    return display.getHasCalendarDropHandlers().addDropHandler(handler);
  }

  @Override
  public HandlerRegistration addCalendarChangeHandler(CalendarChangeHandler handler) {
    return display.getHasCalendarChangeHandlers().addCalendarChangeHandler(handler);
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
  public void addCalendarEvent(TeamTaskEvent event) {
    calendarContent.addCalendarEvent(columns,event);

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
