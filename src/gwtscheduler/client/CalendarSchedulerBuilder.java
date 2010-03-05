package gwtscheduler.client;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.DateViewsTabPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarSchedulerBuilder {

  private DateViewsTabPanel dateViewsTabPanel;
  private List<CalendarPresenter> tabs = new ArrayList<CalendarPresenter>();
  private EventBus eventBus;

  // TODO: {lazo} event bus added for testing
  public CalendarSchedulerBuilder(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public CalendarSchedulerBuilder addTab(CalendarPresenter tab) {
    tabs.add(tab);
    return this;
  }

  public DateViewsTabPanel build(){
     dateViewsTabPanel = new DateViewsTabPanel(tabs, eventBus);
    return dateViewsTabPanel;
  }

}
