package gwtscheduler.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.GwtSchedulerConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.SchedulerCssResource;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decorator.CalendarTitlesRenderer;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.common.navigation.TabPanelContainer;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.ColumnClickedEventHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsFrameGrid;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import gwtscheduler.client.widgets.view.columns.ColumnsViewPresenter;
import gwtscheduler.client.widgets.view.columns.ColumnsViewWidget;
import gwtscheduler.client.widgets.view.common.CollisionDetector;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.common.IntervalCollisionDetector;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeHelperProviderImpl;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import gwtscheduler.client.widgets.view.event.multiColumns.MultiColumnProvider;
import gwtscheduler.client.widgets.view.weekcolumns.WeekDaysColumnsProvider;
import gwtscheduler.common.calendar.IntervalType;
import gwtscheduler.common.util.DateTime;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler extends Composite implements HasWidgets {

  /**
   * ui binder interface
   */
  /**
   * static ref to css
   */
  static {
    Resources.injectAllStylesheets();
  }

  protected static final SchedulerCssResource CSS = Resources.schedulerCss();
  /**
   * widget delegate
   */
  private TabPanelContainer calendarContainer = new TabPanelContainer();

  private GwtSchedulerConfiguration configuration;

  private String name;

  private CalendarPresenter calendar;

  private CollisionDetector collisionDetector = new IntervalCollisionDetector();
  private EventBus eventBus = new EventBus();
  private CalendarTitlesRenderer titlesRenderer = new CalendarTitlesRenderer();
  private CalendarHeader calendarHeader = new CalendarHeader();

  private DateTime selectedDate = new DateTime(new Date()).trimToStart();

  public GwtScheduler() {
    initWidget(calendarContainer);
    calendarContainer.addStyleName(CSS.gwtScheduler());
  }

  @Override
  public void add(Widget w) {
    calendarContainer.add(w);
  }

  @Override
  public void clear() {
    calendarContainer.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return calendarContainer.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return calendarContainer.remove(w);
  }

  public void navigateToDate(Date date) {
    selectedDate = new DateTime(date).trimToStart();

    if (calendar != null) {
      calendar.navigateToDateTime(selectedDate.trimToStart());
    }
  }

  public void setConfiguration(GwtSchedulerConfiguration configuration) {
    this.configuration = configuration;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setWeekColumnView() {
    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.WEEK, selectedDate, configuration.startHour(), configuration.endHour());
    CalendarColumnsProvider columnsProvider = new WeekDaysColumnsProvider(dateGenerator);
    calendar = build(CalendarType.WEEKCOLUMN, columnsProvider, dateGenerator);
  }


  public void setMultiColumnView(List<CalendarColumn> columns) {
    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.DAY, selectedDate, configuration.startHour(), configuration.endHour());
    calendar = build(CalendarType.MULTYCOLUMN, new MultiColumnProvider(columns), dateGenerator);
  }

  public CalendarPresenter build(final CalendarType type, final CalendarColumnsProvider columnsProvider, DateGenerator dateGenerator) {
    eventBus = new EventBus();
    CalendarEventResizeHelperProviderImpl resizeHelper = new CalendarEventResizeHelperProviderImpl(dateGenerator, eventBus);
    //check that functionality with drag zone = null
    CalendarContent calendarContent = new CalendarContent(new CalendarColumnsFrameGrid(), new EventsDashboard(dateGenerator, collisionDetector, eventBus, resizeHelper, null, configuration.intervalsPerHour()));

    calendar = new ColumnsViewPresenter(columnsProvider, dateGenerator, titlesRenderer, calendarHeader, calendarContent, eventBus);


    //waiting the object to be build.   making sure that the widget creation is finished
//    Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
//      @Override
//      public void execute() {
    ColumnsViewWidget display = new ColumnsViewWidget(configuration.rowsInDay(),
            columnsProvider.getColumns().size(),
            configuration.daysLineHeightEMs(),
            calendarContainer.getElement().getClientWidth(),
            calendarContainer.getElement().getClientHeight(),
            eventBus);

    calendar.bindDisplay(display);
    calendar.setCalendarType(type);
    calendar.navigateToDateTime(selectedDate);
    calendar.setTittle(name);

    calendarContainer.clear();
    calendarContainer.add(display);
//      }
//    });

    int hours = configuration.endHour() - configuration.startHour();
    display.scrollToHour(configuration.scrollToHour(), hours);

    return calendar;
  }

  public void updateEvent(Event event) {
    calendar.updateEvent(event);
  }

  public void addEvent(Event event) {
    calendar.addCalendarEvent(event);
  }

  public void deleteEvent(Event event) {
    calendar.deleteEvent(event);
  }

  public void addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    calendar.addCalendarObjectMoveHandler(handler);
  }

  public void addCalendarDropHandler(CalendarDropHandler handler) {
    calendar.addCalendarDropHandler(handler);
  }

  public void addEventDurationIntervalUpdateHandler(CalendarEventDurationChangeHandler handler) {
    calendar.addEventDurationChangeHandler(handler);
  }

  public void addEventResizeStartHandler(CalendarEventDurationChangeStartHandler handler) {
    calendar.addEventDurationChangeStartHandler(handler);
  }

  public void addEventDeleteEventHandler(EventDeleteEventHandler handler) {
    calendar.addEventDeleteEventHandler(handler);
  }

  public void addEventClickHandler(EventClickHandler handler) {
    calendar.addEventClickHandler(handler);
  }

  public void addColumnClickedEventHandler(ColumnClickedEventHandler handler) {
    calendar.addColumnClickedEventHandler(handler);
  }

  public void deleteColumn(CalendarColumn column) {
    calendar.removeColumn(column);
  }

  public void addColumn(CalendarColumn column) {
    calendar.addColumn(column);
  }

  public void setEnable(boolean enable) {
    calendar.setEnable(enable);
  }

  public void clearEvents() {
    calendar.clearEvents();
  }
}