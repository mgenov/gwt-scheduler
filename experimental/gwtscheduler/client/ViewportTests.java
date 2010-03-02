package gwtscheduler.client;

import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.navigation.DateViewsTabPanel;

import org.goda.time.DateTimeConstants;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today;

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    // let's test a registration
    final AppInjector uiResources = AppInjector.GIN.getInjector();
    final UIManager registry = uiResources.getUIRegistry();

    DateViewsTabPanel main = uiResources.getMainPanel();
    //    DateViewsTabPanel main = new DateViewsTabPanel();
    //    for (CalendarPresenter controller : registry.getControllers()) {
    //      main.add(controller);
    //    }

    back = new Button("&laquo;", this);
    forward = new Button("&raquo;", this);
    today = new Button("today", this);
    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);


    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();
    main = schedulerBuilder.addTab(new Calendars().newMultiColumn(new TestAppConfiguration(),new ColumnTitleProvider(){
      @Override
      public String[] getColumns(int columnCount) {
        String[] names = new String[3];
        names[0] = "aaaa";
        names[1] = "bbbb";
        names[2] = "cccc";
        return names;
      }
    },new EventBus()).columns(3).build()).build();


    RootPanel.get("nav").add(nav);
    RootPanel.get("main").add(main);
    main.selectTab(0);
    registry.fireDateNavigation(getCurrentDate());
  }


//  // in evo adm
//  public void ourEntryPoint() {
//    GwtScheduler scheduler = new CalendarBuilder().withColumns()
//            .col("ГОРНА").ofType(ColumnType.TEAMS).build();
//
//  }
//
//
//  class CalendarBuilder {
//    public GwtScheduler build() {
//      Resources.injectAllStylesheets();
//
//      // let's test a registration
//      final AppInjector uiResources = AppInjector.GIN.getInjector();
//      final UIManager registry = uiResources.getUIRegistry();
//
//      DateViewsTabPanel main = uiResources.getMainPanel();
//
//      return new GwtScheduler(main, registry);
//    }
//  }

  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    return start;
  }

  public void onClick(ClickEvent event) {
    AppInjector uiResources = AppInjector.GIN.getInjector();
    UIManager registry = uiResources.getUIRegistry();

    if (event.getSource() == back) {
      registry.fireBackNavigation();
    } else if (event.getSource() == forward) {
      registry.fireForwardNavigation();
    } else if (event.getSource() == today) {
      registry.fireDateNavigation(getCurrentDate());
    }

  }


  public static class TestAppConfiguration implements AppConfiguration {
    public TestAppConfiguration() {
    }

    @Override
    public int startDayOfWeek() {
      return DateTimeConstants.MONDAY;
    }


    @Override
    public int getDayViewTopRows() {
      return 3;
    }


    @Override
    public int daysInWeek() {
      return 7;
    }

    @Override
    public int daysLineHeightEMs() {
      return 2;
    }

    @Override
    public int rowsInDay() {
      return 48;
    }
  }

}
