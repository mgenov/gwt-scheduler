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
  private List<CalendarPresenter> tabs = new ArrayList<CalendarPresenter>();


  public CalendarSchedulerBuilder() {
  }

  public CalendarSchedulerBuilder addTab(CalendarPresenter tab) {
    tabs.add(tab);
    return this;
  }

  public DateViewsTabPanel build(){
     dateViewsTabPanel = new DateViewsTabPanel(tabs);
    return dateViewsTabPanel;
  }

}
