package gwtscheduler.client;

import com.google.inject.Inject;
import com.google.inject.Provider;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.decoration.MultipleElementsIntervalDecorator;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.decorator.DateTimeLabelDecorator;
import gwtscheduler.client.widgets.view.MultiColumnPresenter;
import gwtscheduler.client.widgets.view.columns.ColumnsViewWidget;
import gwtscheduler.client.widgets.view.common.AbstractCalendarPresenter;
import gwtscheduler.client.widgets.view.dayweek.AbstractDaysView;


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
    rows = configuration.daysLineHeightEMs();
    MultipleElementsIntervalDecorator decorator = new DateTimeLabelDecorator();
    calendar = new  MultiColumnPresenter(configuration,decorator,columnTitleProvider,eventBus);
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
    return calendar;
  }
}
