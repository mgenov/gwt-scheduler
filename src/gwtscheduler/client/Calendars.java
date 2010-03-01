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


  private static CalendarPresenter calendar;
  private AppConfiguration configuration;


  public Calendars newMultiColumn(AppConfiguration configuration,ColumnTitleProvider columnTitleProvider, EventBus eventBus) {
    this.configuration = configuration;
    MultipleElementsIntervalDecorator decorator = new DateTimeLabelDecorator();
    calendar = new  MultiColumnPresenter(configuration,decorator,columnTitleProvider,eventBus);
    return this;
  }

  public Calendars named(String title){
    calendar.setTabLabel(title);
    return this;
  }
  public Calendars columnsNames(String[] names){
    calendar.setColNum(names.length);
    return this;
  }


  public CalendarPresenter build(){
    CalendarPresenter.Display display = new ColumnsViewWidget(48,);
    return calendar;
  }
}
