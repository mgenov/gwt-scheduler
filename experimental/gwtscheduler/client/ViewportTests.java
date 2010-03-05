package gwtscheduler.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import gwtscheduler.client.dragndrop.Dragger;
import gwtscheduler.client.dragndrop.DraggerImpl;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.views.UIManager;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.common.decorator.ColumnTitleProvider;
import gwtscheduler.client.widgets.common.navigation.DateViewsTabPanel;

import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import org.goda.time.DateTimeConstants;
import org.goda.time.Interval;
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
  private EventBus eventBus = new EventBus();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    // let's test a registration
//    final AppInjector uiResources = AppInjector.GIN.getInjector();
//    final UIManager registry = uiResources.getUIRegistry();

//    DateViewsTabPanel main = uiResources.getMainPanel();
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


    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder(eventBus);
    DateViewsTabPanel main = schedulerBuilder.addTab(new Calendars().newMultiColumn(new TestAppConfiguration(), new ColumnTitleProvider() {
      @Override
      public String[] getColumns(int columnCount) {
        String[] names = new String[4];
        names[0] =  "Team 1";
        names[1] =  "Team 2";
        names[2] =  "Team 3";
        names[3] =  "Team 4";
//        names[4] =  "Team 5";
        return names;
      }

      @Override
      public void setInterval(Interval interval) {
        
      }
    }, eventBus).columns(4).named("Teams").build()).addTab(new Calendars().newMultiColumn(new TestAppConfiguration(), new ColumnTitleProvider() {
      @Override
      public String[] getColumns(int columnCount) {
        String[] names = new String[15];
        names[0] =  "Team 1";
        names[1] =  "Team 2";
        names[2] =  "Team 3";
        names[3] =  "Team 4";
        names[4] =  "Team 5";
        names[5] =  "Team 6";
        names[6] =  "Team 7";
        names[7] =  "Team 8";
        names[8] =  "Team 9";
        names[9] =  "Team 10";
        names[10] =  "Team 11";
        names[11] =  "Team 12";
        names[12] =  "Team 13";
        names[13] =  "Team 14";
        names[14] =  "Team 15";
        return names;
      }

      @Override
      public void setInterval(Interval interval) {

      }
    }, eventBus).columns(15).named("Teams2").build()).build();

    TicketPresenter ticket = new TicketPresenter(new TicketView2());

    AbsolutePanel absolutePanel = new AbsolutePanel();
    absolutePanel.setSize("100%", "100%");
    Dragger dragger = new DraggerImpl(absolutePanel);
    ticket.go(dragger, 200, 0);

    // TODO: {lazo} just testing
    absolutePanel.add(nav);
    absolutePanel.add(main);

    RootPanel.get("nav").add(absolutePanel);
//    RootPanel.get("main").add(main);
    main.selectTab(0);
//    registry.fireDateNavigation(getCurrentDate());
   eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
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
//      registry.fireBackNavigation();
      eventBus.fireEvent(new NavigatePreviousEvent());
    } else if (event.getSource() == forward) {
//      registry.fireForwardNavigation();
      eventBus.fireEvent(new NavigateNextEvent());
    } else if (event.getSource() == today) {
//      registry.fireDateNavigation(getCurrentDate());
      eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
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
