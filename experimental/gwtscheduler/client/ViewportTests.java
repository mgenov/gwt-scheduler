package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import datepickernavigation.client.DatePickerNavigation;
import dragndrop.client.core.DragZone;
import dragndrop.client.core.Zones;
import gwtscheduler.client.dialog.TestTaskDialog;
import gwtscheduler.client.dialog.TestTaskDialogWidget;
import gwtscheduler.common.event.colors.DefaultColors;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.resources.Resources;

import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.common.navigation.NavigateToEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropEvent;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEvent;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationIntervaUpdateHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationIntervalUpdateEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.DurationInterval;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventClickEvent;
import gwtscheduler.common.event.EventClickHandler;
import org.goda.time.DateTime;
import org.goda.time.DateTimeConstants;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today, deleteColumn, addColumn, enable,disable;
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
    enable = new Button("enable", this);
    disable = new Button("disable", this);


    HorizontalPanel nav = new HorizontalPanel();
    DatePickerNavigation datePicker = new DatePickerNavigation();
    nav.add(datePicker);
    nav.add(back);
    nav.add(today);
    nav.add(forward);
    nav.add(textBox);
    nav.add(deleteColumn);
    nav.add(addColumn);
    nav.add(enable);
    nav.add(disable);

    nav.add(ticketsPanel);

    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date date = event.getValue();
        main.navigateToDate(date);
      }
    });


    CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();

    main = schedulerBuilder.addTab(new CalendarsBuilder().newMultiColumn(new TestAppConfiguration(), testteams1, dragZone).named("Teams").build())
            .addTab(new CalendarsBuilder().newWeekColumn(new TestAppConfiguration(), dragZone).named("Team 1 Week Calendar").build()).build();

    dragZone.addDropZoneRoot((HasWidgets) main.asWidget());

    VerticalPanel mainPanel = new VerticalPanel();
//    mainPanel.makeDraggable(dropRoot);
    mainPanel.add(ticketsPanel);
    mainPanel.add(nav);
    mainPanel.add(main.asWidget());

//    VerticalPanel testPanel = new VerticalPanel();
//    testPanel.add(new Label("Wazaaaap"));
//    dragZone.addWidget(testPanel);
    dragZone.addWidget(mainPanel);
    dragZone.go(RootPanel.get());
//    dragZone.go(testPanel);
//    RootPanel.attachResizeHelper().add(testPanel);

    final TestTaskDialog dialog = new TestTaskDialog();
    TestTaskDialogWidget display = new TestTaskDialogWidget();
    dialog.bindDisplay(display);


    main.addCalendarObjectMoveHandler(new CalendarObjectMoveHandler() {
      @Override
      public void onCalendarObjectMove(CalendarObjectMoveEvent event) {
        Object o = event.getDroppedObject();
        if(o instanceof TestTask){
//          GWT.log("On calendar type: " + event.getAssociatedType().toString(), null);
//          GWT.log("On calendar with title: " + event.getCalendarTitle(), null);
//          GWT.log("From column with title: " + event.getOldColumn().getTitle(), null);
//          GWT.log("To column with title: " + event.getNewColumn().getTitle(), null);
//          GWT.log("From time: " + event.getOldTime().toString(), null);
//          GWT.log("To time: " + event.getNewTime().toString(), null);
        } else if(o instanceof CalendarEvent) {
          CalendarEvent calendarEvent = (CalendarEvent)event.getDroppedObject();
          TeamTaskEvent teamEvent = (TeamTaskEvent)calendarEvent.getEvent();
          // change column
          teamEvent.setColumn(event.getNewColumn());
          // change time
          Date currentStart = teamEvent.getDurationInterval().getStart();
          Date currentEnd = teamEvent.getDurationInterval().getEnd();
          long oldTime = event.getOldTime();
          long newTime = event.getNewTime();
          long difference;
          if(oldTime>newTime){
            difference = oldTime - newTime;
//            teamEvent.setInterval(new Interval(currentStart.minus(difference), currentEnd.minus(difference)));
            teamEvent.setDurationInterval(DurationInterval.getInterval(currentStart.getTime()-difference, currentEnd.getTime() - difference));
          } else {
            difference = newTime - oldTime;
//            teamEvent.setInterval(new Interval(currentStart.plus(difference), currentEnd.plus(difference)));
            teamEvent.setDurationInterval(DurationInterval.getInterval(currentStart.getTime() + difference, currentEnd.getTime() + difference));
          }

          main.updateEvent(teamEvent);
        }
      }
    });

    main.addCalendarDropHandler(new CalendarDropHandler() {
      @Override
      public void onCalendarDrop(CalendarDropEvent event) {
        Object o = event.getDroppedObject();
        CalendarColumn column = event.getCalendarColumn();
        
        if(o instanceof TestTask){
//          GWT.log("Dropped: TicketPresenter", null);
//          GWT.log("On calendar type: " + event.getCalendarType().toString(), null);
//          GWT.log("On calendar with title: " + event.getCalendarTitle(), null);
//          GWT.log("On column with title: " + event.getCalendarColumn().getTitle(), null);
//          GWT.log("On time: " + event.getDropTime().toString(), null);

          TestTask testTask = (TestTask) o;
//          Interval interval = new Interval(event.getDropTime(), event.getDropTime().plus(3600 * testTask.getDuration() * 1000));
//          testTask.setInterval(interval);
          testTask.setDurationInterval(DurationInterval.getInterval(event.getDropTime(), event.getDropTime() + 3600 * testTask.getDuration() * 1000));
          dialog.setTestTask(testTask, column);
          dialog.show();
        }
      }
    });

    main.addEventDurationIntervalUpdateHandler(new CalendarEventDurationIntervaUpdateHandler(){
      @Override
      public void onCalendarEventDurationIntervalUpdate(CalendarEventDurationIntervalUpdateEvent event) {
//        GWT.log("Resized event" + event.getCalendarEvent().getEventTitle(), null);
//        GWT.log("Event from" + event.getStartTime(), null);
//        GWT.log("Event to" + event.getEndTime(), null);
        Event calendarEvent = event.getEvent();
        calendarEvent.setDurationInterval(DurationInterval.getInterval(event.getStartTime(), event.getEndTime()));
        main.updateEvent(calendarEvent);
      }
    });

    main.addEventResizeStartHandler(new CalendarEventResizeStartHandler(){
      @Override
      public void onCalendarEventResizeStartEvent(CalendarEventResizeStartEvent event) {
//        GWT.log("Event resizing start:", null);
      }
    });

    // used to chenge every time the color of the event
    final int[] b = {1};
    dialog.getOKButton().addClickHandler(new ClickHandler(){
      @Override
      public void onClick(ClickEvent event) {
        TestTask testTask = dialog.getTestTask();
        CalendarColumn column = dialog.getColumn();

        TeamTaskEvent teamTaskEvent;
        if(b[0]==1){
         teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultColors.getRedEventColor());
          b[0] = 2;
        }else if(b[0]==2){
         teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultColors.getBlueEventColor());
          b[0] = 3;
        }else if(b[0]==3){
          teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultColors.getGreenEventColor());
           b[0] = 4;
         }else {//if(b[0]==4)
           teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultColors.getYellowEventColor());
          b[0] = 1;
        }
        testTask.setDescription(testTask.getDescription()+ "  event id = "+teamTaskEvent.getEventId());
        main.addEvent(teamTaskEvent);
        dialog.close();
      }
    });
    main.addEventDeleteEventHandler(new EventDeleteEventHandler(){
      @Override
      public void onEventDelete(EventDeleteEvent e) {
        main.deleteEvent(e.getEvent());
      }
    });

    main.addEventClickHandler(new EventClickHandler(){
      @Override
      public void onEventClickEvent(EventClickEvent event) {
//        GWT.log("Clicked on event: " + event.getEvent().getTitle(), null);
        final DialogBox dialogBox = new DialogBox();
        dialogBox.getElement().getStyle().setZIndex(50);
        VerticalPanel vp = new VerticalPanel();
        vp.add(new Label("Clicked event : "+ event.getEvent().getEventId()));

        Button button = new Button("Close");
        vp.add(button);
        button.addClickHandler(new ClickHandler(){
          @Override
          public void onClick(ClickEvent event) {
            dialogBox.hide();
          }
        });
        dialogBox.add(vp);
        dialogBox.center();
      }
    });


    main.selectTab(0);
    main.navigateToDate(new Date(getCurrentDate().getMillis()));
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

    if (event.getSource() == back) {
      eventBus.fireEvent(new NavigatePreviousEvent());
    } else if (event.getSource() == forward) {
      eventBus.fireEvent(new NavigateNextEvent());
    } else if (event.getSource() == today) {
      eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
    } else if (event.getSource() == deleteColumn) {
      CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
      main.deleteColumn(column);
    } else if (event.getSource() == addColumn) {
      if (!textBox.getText().equals("")) {
        CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
        main.addColumn(column);
      }
    } else if (event.getSource() == disable) {
      main.setEnable(false);
    } else if (event.getSource() == enable) {
      main.setEnable(true);
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
