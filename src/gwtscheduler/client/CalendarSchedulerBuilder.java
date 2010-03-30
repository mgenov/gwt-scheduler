package gwtscheduler.client;

import com.google.inject.name.Names;
import dragndrop.client.core.DragZone;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;

import java.util.ArrayList;
import java.util.List;

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


  public CalendarSchedulerBuilder() {
  }

  public CalendarSchedulerBuilder multiColumnScheduler(AppConfiguration configuration, CalendarColumnsProvider columnsProvider, DragZone dragZone) {
    presenter = new CalendarsBuilder().newMultiColumn(configuration, columnsProvider, dragZone).build();
    return this;
  }

  public CalendarSchedulerBuilder weekColumnScheduler(AppConfiguration configuration, DragZone dragZone) {
    presenter = new CalendarsBuilder().newWeekColumn(configuration, dragZone).build();
    return this;
  }

  public CalendarSchedulerBuilder named(String name) {
    presenter.setTittle(name);
    return this;
  }

  public GwtScheduler build() {
    gwtScheduler = new GwtScheduler(presenter);
    gwtSchedulerWidget = new GwtSchedulerWidget();
    gwtScheduler.bindDisplay(gwtSchedulerWidget);
    return gwtScheduler;
  }
}
