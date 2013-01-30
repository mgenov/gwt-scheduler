package gwtscheduler.client;

import dragndrop.client.core.DragZone;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;

/**
 * Builds calendar scheduler
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarSchedulerBuilder {
  CalendarPresenter presenter;

//  private List<CalendarPresenter> presenters = new ArrayList<CalendarPresenter>();

  private GwtScheduler gwtScheduler;
  private GwtSchedulerWidget gwtSchedulerWidget;
  private AppConfiguration configuration;
  private CalendarColumnsProvider columnsProvider;
  private DragZone dragZone;
  private boolean multyColumnCalendar = false;
  private String name;


  public CalendarSchedulerBuilder() {
    Resources.injectAllStylesheets();
  }

  public CalendarSchedulerBuilder multiColumnScheduler(AppConfiguration configuration, CalendarColumnsProvider columnsProvider, DragZone dragZone) {
    this.configuration = configuration;
    this.columnsProvider = columnsProvider;
    this.dragZone = dragZone;
    multyColumnCalendar = true;

    return this;
  }

  public CalendarSchedulerBuilder weekColumnScheduler(AppConfiguration configuration, DragZone dragZone) {
    this.configuration = configuration;
    this.dragZone = dragZone;

    return this;
  }

  public CalendarSchedulerBuilder named(String name) {
    this.name = name;
    return this;
  }

  public GwtScheduler build() {
    gwtSchedulerWidget = new GwtSchedulerWidget(configuration, columnsProvider.getColumns().size());

    if(multyColumnCalendar){
      presenter = new CalendarsBuilder().newMultiColumn(configuration, columnsProvider, dragZone).build();
    }else {
      presenter = new CalendarsBuilder().newWeekColumn(configuration, dragZone).build();
    }
    presenter.setTittle(name);

//    CalendarPresenter.Display display = new ColumnsViewWidget(configuration.rowsInDay(), columnsProvider.getColumns().size(), configuration.daysLineHeightEMs());
//    calendar.bindDisplay(display);


    gwtScheduler = new GwtScheduler(presenter);
    gwtScheduler.bindDisplay(gwtSchedulerWidget);
    presenter.forceLayout();
    return gwtScheduler;
  }
}
