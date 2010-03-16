package gwtscheduler.client;

import dragndrop.client.core.DragZone;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsFrameGrid;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import gwtscheduler.client.widgets.view.columns.ColumnsViewPresenter;
import gwtscheduler.client.widgets.view.columns.ColumnsViewWidget;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.weekcolumns.WeekDaysColumnsProvider;
import gwtscheduler.common.calendar.IntervalType;
import org.goda.time.DateTime;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import java.util.Date;


/**
 * Builds Calendars
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarsBuilder {


  private CalendarPresenter calendar;
  private AppConfiguration configuration;
  private int columns;
  private int rows;
  private int daysLineHeightEMs;

  /**
   * Sets a new multy column calednar in the builder.
   * @param configuration
   * @param columnsProvider
   * @param eventBus
   * @return
   */
  public CalendarsBuilder newMultiColumn(AppConfiguration configuration, CalendarColumnsProvider columnsProvider, EventBus eventBus, DragZone dragZone) {
    this.configuration = configuration;
    rows = configuration.rowsInDay();
    columns = columnsProvider.getColumns().size();
    daysLineHeightEMs = configuration.daysLineHeightEMs();

    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.DAY, getCurrentDate());

    CalendarTitlesRenderer titlesRenderer = new CalendarTitlesRenderer();
    CalendarHeader calendarHeader = new CalendarHeader();

    CalendarContent calendarContent = new CalendarContent(new CalendarColumnsFrameGrid(), new EventsDashboard(dateGenerator, eventBus, dragZone));

    calendar = new ColumnsViewPresenter(columnsProvider, dateGenerator, titlesRenderer, calendarHeader, calendarContent, eventBus);

    calendar.setCalendarType(CalendarType.MULTYCOLUMN);

    return this;
  }

  /**
   * Sets a new week column calednar in the builder.
   * @param configuration
   * @param eventBus
   * @return
   */
  public CalendarsBuilder newWeekColumn(AppConfiguration configuration, EventBus eventBus, DragZone dragZone) {
    this.configuration = configuration;
    rows = configuration.rowsInDay();
    daysLineHeightEMs = configuration.daysLineHeightEMs();

    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.WEEK, getCurrentDate());

    CalendarColumnsProvider columnsProvider = new WeekDaysColumnsProvider(dateGenerator);
    columns = columnsProvider.getColumns().size();


    CalendarTitlesRenderer titlesRenderer = new CalendarTitlesRenderer();
    CalendarHeader calendarHeader = new CalendarHeader();

    CalendarContent calendarContent = new CalendarContent(new CalendarColumnsFrameGrid(),new EventsDashboard(dateGenerator,eventBus, dragZone));

    calendar = new ColumnsViewPresenter(columnsProvider, dateGenerator, titlesRenderer, calendarHeader, calendarContent, eventBus);

    calendar.setCalendarType(CalendarType.WEEKCOLUMN);

    return this;
  }

  /**
   * sets title for the calendar
   * @param title
   * @return
   */
  public CalendarsBuilder named(String title) {
    calendar.setTittle(title);
    return this;
  }

  /**
   * Builds the widget for the calendar and bind it to the calendar
   * @return
   */
  public CalendarPresenter build() {
    CalendarPresenter.Display display = new ColumnsViewWidget(rows, columns, daysLineHeightEMs);
    calendar.bindDisplay(display);
    return calendar;
  }


  /**
   * Gets the current date time 
   * @return
   */
  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    DateTime date = start.toDateTime();
    return date;
  }
}
