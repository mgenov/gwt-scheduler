package gwtscheduler.client;

import com.google.gwt.user.client.ui.HasWidgets;
import gwtscheduler.client.widgets.common.CalendarPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds calendar scheduler
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class CalendarSchedulerBuilder {

  private List<CalendarPresenter> presenters = new ArrayList<CalendarPresenter>();

  private GwtScheduler gwtScheduler;
  private GwtSchedulerWidget gwtSchedulerWidget;


  public CalendarSchedulerBuilder() {
  }

  /**
   * Adds new calendar to the presenter.
   * @param presenter
   * @return
   */
  public CalendarSchedulerBuilder addTab(CalendarPresenter presenter) {
    presenters.add(presenter);
    return this;
  }

  /**
   * build the calendar scheduler
   * @return
   */
  public GwtScheduler build(){
    gwtScheduler = new GwtScheduler(presenters);
    gwtSchedulerWidget = new GwtSchedulerWidget();
    gwtScheduler.bindDisplay(gwtSchedulerWidget);

    return gwtScheduler;
  }
}
