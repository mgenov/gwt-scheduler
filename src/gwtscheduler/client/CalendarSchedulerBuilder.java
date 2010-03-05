package gwtscheduler.client;

import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.DateViewsTabPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarSchedulerBuilder {

  private DateViewsTabPanel dateViewsTabPanel;
  private List<CalendarPresenter> presenters = new ArrayList<CalendarPresenter>();

  private GwtScheduler gwtScheduler;
  private GwtSchedulerWidget gwtSchedulerWidget;


  public CalendarSchedulerBuilder() {
  }

  public CalendarSchedulerBuilder addTab(CalendarPresenter presenter) {
    presenters.add(presenter);
    return this;
  }

  public GwtScheduler build(){
//     dateViewsTabPanel = new DateViewsTabPanel(presenters);
    gwtScheduler = new GwtScheduler(presenters);
    gwtSchedulerWidget = new GwtSchedulerWidget();
    gwtScheduler.bindDisplay(gwtSchedulerWidget);

    return gwtScheduler;
  }

}
