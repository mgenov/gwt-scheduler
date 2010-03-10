package gwtscheduler.client;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;

import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.cobogw.gwt.user.client.Color;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;
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

  Button back, forward, today,deleteColumn,addColumn;
  TextBox textBox = new TextBox();
  private EventBus eventBus = new EventBus();

   GwtScheduler main;
  private TestTeamCalendarColumnProvider testteams1 = new TestTeamCalendarColumnProvider();

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
    deleteColumn = new Button("delete Column",this);
    addColumn = new Button("add Column",this);


    HorizontalPanel nav = new HorizontalPanel();
    nav.add(back);
    nav.add(today);
    nav.add(forward);
    nav.add(textBox);
    nav.add(deleteColumn);
    nav.add(addColumn);


    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();
//    GwtScheduler main = schedulerBuilder.addTab(new Calendars().newMultiColumn(new TestAppConfiguration(), new ColumnTitleProvider() {
//      @Override
//      public String[] getColumns(int columnCount) {
//        String[] names = new String[4];
//        names[0] =  "Team 1";
//        names[1] =  "Team 2";
//        names[2] =  "Team 3";
//        names[3] =  "Team 4";
////        names[4] =  "Team 5";
//        return names;
//      }
//
//      @Override
//      public void setInterval(Interval interval) {
//
//      }
//    }, eventBus).columns(4).named("Teams").build()).addTab(new Calendars().newMultiColumn(new TestAppConfiguration(), new ColumnTitleProvider() {
//      @Override
//      public String[] getColumns(int columnCount) {
//        String[] names = new String[15];
//        names[0] =  "Team 1";
//        names[1] =  "Team 2";
//        names[2] =  "Team 3";
//        names[3] =  "Team 4";
//        names[4] =  "Team 5";
//        names[5] =  "Team 6";
//        names[6] =  "Team 7";
//        names[7] =  "Team 8";
//        names[8] =  "Team 9";
//        names[9] =  "Team 10";
//        names[10] =  "Team 11";
//        names[11] =  "Team 12";
//        names[12] =  "Team 13";
//        names[13] =  "Team 14";
//        names[14] =  "Team 15";
//        return names;
//      }
//
//      @Override
//      public void setInterval(Interval interval) {
//
//      }
//    }, eventBus).columns(15).named("Teams2").build()).build();


    main = schedulerBuilder.addTab(new Calendars().newMultiColumn(new TestAppConfiguration(), testteams1,eventBus).named("Teams").build()).build();

    RootPanel.get("nav").add(nav);
    RootPanel.get("main").add(main.asWidget());
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
//    AppInjector uiResources = AppInjector.GIN.getInjector();
//    UIManager registry = uiResources.getUIRegistry();

    if (event.getSource() == back) {
//      registry.fireBackNavigation();
      eventBus.fireEvent(new NavigatePreviousEvent());
    } else if (event.getSource() == forward) {
//      registry.fireForwardNavigation();
      eventBus.fireEvent(new NavigateNextEvent());
    } else if (event.getSource() == today) {
//      registry.fireDateNavigation(getCurrentDate());
      eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
    }else if(event.getSource() == deleteColumn){
      CalendarColumn  column = testteams1.getColumn(textBox.getText());
        main.deleteColumn(column);
    }else if(event.getSource() == addColumn){
      if(textBox.getText()!=""){
      CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
        main.addColumn(column);
      }
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
