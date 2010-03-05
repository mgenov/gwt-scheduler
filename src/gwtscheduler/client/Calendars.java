package gwtscheduler.client;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decorator.ColumnStrategyDecorationRenderer;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.decorator.DateTimeLabelDecorator;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import gwtscheduler.client.widgets.view.columns.ColumnsViewPresenter;
import gwtscheduler.client.widgets.view.columns.ColumnsViewWidget;
import gwtscheduler.common.calendar.IntervalType;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;


/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class Calendars {


  private CalendarPresenter calendar;
  private AppConfiguration configuration;
  private int columns;
  private int rows;


  public Calendars newMultiColumn(AppConfiguration configuration,ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
    this.configuration = configuration;
    rows = configuration.rowsInDay();
    DateTimeLabelDecorator decorator = new DateTimeLabelDecorator();
    ColumnStrategyDecorationRenderer decorationRenderer  = new ColumnStrategyDecorationRenderer(decorator,columnTitleProvider);
    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.DAY,getCurrentDate());
    calendar = new ColumnsViewPresenter(dateGenerator,decorationRenderer,eventBus);
    return this;
  }

  public Calendars newMultiColumn(AppConfiguration configuration, CalendarColumnsProvider columnsProvider, EventBus eventBus) {
    this.configuration = configuration;
    rows = configuration.rowsInDay();
    columns = columnsProvider.getColumns().size();
    DateTimeLabelDecorator decorator = new DateTimeLabelDecorator();
//    ColumnStrategyDecorationRenderer decorationRenderer  = new ColumnStrategyDecorationRenderer(decorator,columnTitleProvider);
    DateGenerator dateGenerator = new GenericDateGenerator();
    dateGenerator.init(IntervalType.DAY,getCurrentDate());
    calendar = new ColumnsViewPresenter(dateGenerator,columnsProvider.getColumns(),eventBus);
    return this;
  }



  public Calendars named(String title){
    calendar.setTabLabel(title);
    return this;
  }
  public Calendars columnsNames(String[] names){
    columns = names.length;
    calendar.setColNum(columns);
    return this;
  }

   public Calendars columns(int columns){
    this.columns = columns;
    calendar.setColNum(columns);
    return this;
  }


  public CalendarPresenter build(){
    CalendarPresenter.Display display = new ColumnsViewWidget(rows,columns);
    calendar.bindDisplay(display);
    return calendar;
  }


   protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    return start;
  }
}
