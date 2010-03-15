package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import datepickernavigation.client.DatePickerNavigation;
import dragndrop.client.core.DragZone;
import dragndrop.client.core.Zones;
import gwtscheduler.client.dialog.TestTaskDialog;
import gwtscheduler.client.dialog.TestTaskDialogWidget;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;

import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarChangeEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarChangeHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.DateTime;
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

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today, deleteColumn, addColumn;
  TextBox textBox = new TextBox();
  private EventBus eventBus = new EventBus();

  GwtScheduler main;
  private TestTeamCalendarColumnProvider testteams1 = new TestTeamCalendarColumnProvider();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    Resources.injectAllStylesheets();

    TestTask task = new TestTask();
    task.setDescription("test description");
    task.setTitle("Test Task");
    task.setDuration(2);

    TestTask task2 = new TestTask();
    task2.setDescription("test description  222222");
    task2.setTitle("Test Task2");
    task2.setDuration(5);

    HorizontalPanel ticketsPanel = new HorizontalPanel();
    TicketPresenter ticket1 = new TicketPresenter(new TicketView2(), task);
    TicketPresenter ticket2 = new TicketPresenter(new TicketView2(), task2);
//    TicketPresenter ticket3 = new TicketPresenter(new TicketView2(), "Ticket three");

    ticketsPanel.add(ticket1.getDisplay());
    ticketsPanel.add(ticket2.getDisplay());
//    ticketsPanel.add(ticket3.getDisplay());

    TicketPresenterFrame frame = new TicketPresenterFrame();
    frame.bindDisplay(new TicketPresenterFrameView());
    
    DragZone dragZone = Zones.getDragZone();
    dragZone.add(ticket1);
    dragZone.add(ticket2);
    dragZone.registerFrame(frame, TicketPresenter.class);
//    dragZone.add(ticket3);

    back = new Button("&laquo;", this);
    forward = new Button("&raquo;", this);
    today = new Button("today", this);
    deleteColumn = new Button("delete Column", this);
    addColumn = new Button("add Column", this);


    HorizontalPanel nav = new HorizontalPanel();
    DatePickerNavigation datePicker = new DatePickerNavigation();
    nav.add(datePicker);
    nav.add(back);
    nav.add(today);
    nav.add(forward);
    nav.add(textBox);
    nav.add(deleteColumn);
    nav.add(addColumn);

    nav.add(ticketsPanel);

    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date date = event.getValue();
        DateTime selectedDate = new DateTime(date.getTime());
        eventBus.fireEvent(new NavigateToEvent(selectedDate));
      }
    });


    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();

    main = schedulerBuilder.addTab(new CalendarsBuilder().newMultiColumn(new TestAppConfiguration(), testteams1, eventBus).named("Teams").build())
            .addTab(new CalendarsBuilder().newWeekColumn(new TestAppConfiguration(), eventBus).named("Team 1 Week Calendar").build()).build();

    dragZone.addDropZoneRoot((HasWidgets)main.asWidget());
//    VerticalPanel dropRoot = new VerticalPanel();
//    dropRoot.makeDraggable(new Panel1());
//    dropRoot.makeDraggable(new Panel1());
//    dropRoot.makeDraggable(new Panel1());
//    dragZone.addDropZoneRoot(dropRoot);

    VerticalPanel mainPanel = new VerticalPanel();
//    mainPanel.makeDraggable(dropRoot);
    mainPanel.add(ticketsPanel);
    mainPanel.add(nav);
    mainPanel.add(main.asWidget());

    VerticalPanel testPanel = new VerticalPanel();
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    testPanel.add(new Label("Wazaaaap"));
    dragZone.addWidget(testPanel);
    dragZone.addWidget(mainPanel);
    dragZone.go(RootPanel.get());
//    dragZone.go(testPanel);
//    RootPanel.get().add(testPanel);

    final TestTaskDialog dialog = new TestTaskDialog();
    TestTaskDialogWidget display = new TestTaskDialogWidget();
    dialog.bindDisplay(display);



    main.addCalendarChangeHandler(new CalendarChangeHandler(){
      @Override
      public void onCalendarChange(CalendarChangeEvent event){
        Object o = event.getDroppedObject();
        if(o instanceof TestTask){
          GWT.log("On calendar type: " + event.getAssociatedType().toString(), null);
          GWT.log("On calendar with title: " + event.getCalendarTitle(), null);
          GWT.log("From column with title: " + event.getOldColumn().getTitle(), null);
          GWT.log("To column with title: " + event.getNewColumn().getTitle(), null);
          GWT.log("From time: " + event.getOldTime().toString(), null);
          GWT.log("To time: " + event.getNewTime().toString(), null);
        }
      }
    });

    main.addCalendarDropHandler(new CalendarDropHandler() {
      @Override
      public void onCalendarDrop(CalendarDropEvent event) {
        Object o = event.getDroppedObject();
        CalendarColumn column = event.getCalendarColumn();
        
        if(o instanceof TestTask){
          GWT.log("Dropped: TicketPresenter", null);
          GWT.log("On calendar type: " + event.getCalendarType().toString(), null);
          GWT.log("On calendar with title: " + event.getCalendarTitle(), null);
          GWT.log("On column with title: " + event.getCalendarColumn().getTitle(), null);
          GWT.log("On time: " + event.getDropTime().toString(), null);

          TestTask testTask = (TestTask) o;
          Interval interval = new Interval(event.getDropTime(), event.getDropTime().plus(3600 * testTask.getDuration() * 1000));
          testTask.setInterval(interval);
          dialog.setTestTask(testTask,column);
          dialog.show();
        }
      }
    });

    dialog.getOKButton().addClickHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent event) {
        TestTask testTask =  dialog.getTestTask();
        CalendarColumn column = dialog.getColumn();
        TeamTaskEvent teamTaskEvent = new TeamTaskEvent(testTask,column);
        main.addEvent(teamTaskEvent);
      }
    });


    main.selectTab(0);
//    registry.fireDateNavigation(getCurrentDate());
    eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
  }

  protected ReadableDateTime getCurrentDate() {
    MutableDateTime start = new MutableDateTime();
    start.setHourOfDay(0);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    DateTime date = start.toDateTime();
    return date;
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
    } else if (event.getSource() == deleteColumn) {
      CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
      main.deleteColumn(column);
    } else if (event.getSource() == addColumn) {
      if (!textBox.getText().equals("")) {
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
