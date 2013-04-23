package gwtscheduler.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.*;
import datepickernavigation.client.DatePickerNavigation;
import dragndrop.client.core.DragZone;
import dragndrop.client.core.Zones;
import gwtscheduler.client.dialog.TestTaskDialog;
import gwtscheduler.client.dialog.TestTaskDialogWidget;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.NavigateNextEvent;
import gwtscheduler.client.widgets.common.navigation.NavigatePreviousEvent;
import gwtscheduler.client.widgets.view.calendarevent.*;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartEvent;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickEvent;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import gwtscheduler.client.widgets.view.event.colors.DefaultEventColors;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

import java.util.Date;
import java.util.List;

import static gwtscheduler.client.modules.config.GwtSchedulerConfiguration.aNewGwtSchedulerConfiguration;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ViewportTests implements EntryPoint, ClickHandler {

  Button back, forward, today, deleteColumn, addColumn, enable,disable;
  TextBox textBox = new TextBox();

  private EventBus eventBus = new EventBus();

  GwtScheduler gwtScheduler = new GwtScheduler();
  private TestTeamCalendarColumnProvider testteams1 = new TestTeamCalendarColumnProvider();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
//    Resources.injectAllStylesheets();

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
    //bla
    TicketPresenterFrame frame = new TicketPresenterFrame();
    frame.bindDisplay(new TicketPresenterFrameView());

    DragZone dragZone = Zones.getDragZone(frame);
    dragZone.register(ticket1);
    dragZone.register(ticket2);
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
        gwtScheduler.navigateToDate(date);
      }
    });

    FlowPanel mainPanel = new FlowPanel();
      mainPanel.add(ticketsPanel);
      mainPanel.add(nav);
      mainPanel.add(gwtScheduler);

      dragZone.add(mainPanel);
      dragZone.go(RootPanel.get());


    gwtScheduler.setConfiguration(aNewGwtSchedulerConfiguration()
            .daysInWeek(7)
            .intervalsPerHour(4)
            .hourIntervalHeightEMs(2)
            .setScrollToHour(10)
//            .startHour(0)
//            .endHour(24)
            .build());

    gwtScheduler.setName("Team 1 Week Calendar");

    gwtScheduler.setHeight("400px");
    gwtScheduler.setWidth("1000px");

    List<CalendarColumn> columns = testteams1.getColumns();
    gwtScheduler.setMultiColumnView(columns);
//    gwtScheduler.setWeekColumnView();
    gwtScheduler.navigateToDate(getCurrentDate().asDate());


    dragZone.addDropZoneContainer(gwtScheduler);



    final TestTaskDialog dialog = new TestTaskDialog();
    TestTaskDialogWidget display = new TestTaskDialogWidget();
    dialog.bindDisplay(display);


    gwtScheduler.addCalendarObjectMoveHandler(new CalendarObjectMoveHandler() {
      @Override
      public void onCalendarObjectMove(CalendarObjectMoveEvent event) {
        Object o = event.getDroppedObject();
        if (o instanceof TestTask) {

        } else if (o instanceof CalendarEvent) {
          CalendarEvent calendarEvent = (CalendarEvent) event.getDroppedObject();
          TeamTaskEvent teamEvent = (TeamTaskEvent) calendarEvent.getEvent();
          // change column
          teamEvent.setColumn(event.getNewColumn());
          // change time
          DateTime currentStart = teamEvent.getDurationInterval().getStart();
          DateTime currentEnd = teamEvent.getDurationInterval().getEnd();

          long difference = event.getDifference();


          teamEvent.setDurationInterval(new Period(currentStart.plusMills(difference), currentEnd.plusMills(difference)));


          gwtScheduler.updateEvent(teamEvent);
        }
      }
    });

    gwtScheduler.addCalendarDropHandler(new CalendarDropHandler() {
      @Override
      public void onCalendarDrop(CalendarDropEvent event) {
        Object o = event.getDroppedObject();
        CalendarColumn column = event.getCalendarColumn();

        if (o instanceof TestTask) {

          TestTask testTask = (TestTask) o;

          testTask.setDurationInterval(new Period(new DateTime(event.getDropTimeMills()), new DateTime(event.getDropTimeMills()).plusHours(testTask.getDuration())));
          dialog.setTestTask(testTask, column);
          dialog.show();
        }
      }
    });

    gwtScheduler.addEventDurationIntervalUpdateHandler(new CalendarEventDurationChangeHandler() {
      @Override
      public void onCalendarEventDurationChange(CalendarEventDurationChangeEvent event) {
        Event calendarEvent = event.getEvent();
        calendarEvent.setDurationInterval(new Period(new DateTime(event.getStartTime()), new DateTime(event.getEndTime())));
        gwtScheduler.updateEvent(calendarEvent);
      }
    });

    gwtScheduler.addEventResizeStartHandler(new CalendarEventDurationChangeStartHandler(){
      @Override
      public void onCalendarEventDurationChangeStart(CalendarEventDurationChangeStartEvent event) {
//        GWT.log("Event resizing start:", null);
      }
    });

    // used to chenge every time the color of the event
    final int[] b = {1};
    dialog.getOKButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        TestTask testTask = dialog.getTestTask();
        CalendarColumn column = dialog.getColumn();

        TeamTaskEvent teamTaskEvent;
        if (b[0] == 1) {
          teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultEventColors.getRedEventColor());
          b[0] = 2;
        } else if (b[0] == 2) {
          teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultEventColors.getBlueEventColor());
          b[0] = 3;
        } else if (b[0] == 3) {
          teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultEventColors.getGreenEventColor());
          b[0] = 4;
        } else {//if(b[0]==4)
          teamTaskEvent = new TeamTaskEvent(testTask, column, DefaultEventColors.getYellowEventColor());
          b[0] = 1;
        }
        testTask.setDescription(testTask.getDescription() + "  event id = " + teamTaskEvent.getEventId());
        gwtScheduler.addEvent(teamTaskEvent);
        dialog.close();
      }
    });
    gwtScheduler.addEventDeleteEventHandler(new EventDeleteEventHandler() {
      @Override
      public void onEventDelete(EventDeleteEvent e) {
        gwtScheduler.deleteEvent(e.getEvent());
      }
    });

    final DecoratedPopupPanel panel = new DecoratedPopupPanel(true);

    gwtScheduler.addColumnTitleOverEventHandler(new ColumnTitleOverEventHandler() {
      @Override
      public void onOver(ColumnTitleOverEvent event) {
        panel.clear();
        panel.setPopupPosition(event.getLeft(),event.getTop()+ 20);
        panel.add(new Label(event.getColumn().getTitle()));
//        panel.center();
        panel.show();
        GWT.log("mause over : " + event.getColumn().getTitle());
      }
    });

    gwtScheduler.addColumnTitleOutEventHandler(new ColumnTitleOutEventHandler() {
      @Override
      public void onOut(ColumnTitleOutEvent event) {
        panel.hide();
        GWT.log("mause out : " + event.getColumn().getTitle());
      }
    });

    gwtScheduler.addEventClickHandler(new EventClickHandler(){
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

    gwtScheduler.addColumnClickedEventHandler(new ColumnClickedEventHandler() {
      @Override
      public void onColumnTitleClicked(ColumnClickedEvent event) {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.getElement().getStyle().setZIndex(50);
        VerticalPanel vp = new VerticalPanel();
        vp.add(new Label("Clicked Column with title : " + event.getColumn().getTitle()));
        vp.add(new Label("Clicked Column with  id   : " + event.getColumn().getId()));

        Button button = new Button("Close");
        vp.add(button);
        button.addClickHandler(new ClickHandler() {
          @Override
          public void onClick(ClickEvent event) {
            dialogBox.hide();
          }
        });
        dialogBox.add(vp);
        dialogBox.center();
      }
    });

  }

  protected DateTime getCurrentDate() {
    DateTime start = new DateTime(new Date());
    return start;
  }

  public void onClick(ClickEvent event) {

    if (event.getSource() == back) {
      eventBus.fireEvent(new NavigatePreviousEvent());
    } else if (event.getSource() == forward) {
      eventBus.fireEvent(new NavigateNextEvent());
    } else if (event.getSource() == today) {
//      eventBus.fireEvent(new NavigateToEvent(getCurrentDate()));
    } else if (event.getSource() == deleteColumn) {
      CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
      gwtScheduler.deleteColumn(column);
    } else if (event.getSource() == addColumn) {
      if (!textBox.getText().equals("")) {
        CalendarColumn column = new TestTeamCalendarColumnProvider.TeamColumn(textBox.getText());
        gwtScheduler.addColumn(column);
      }
    } else if (event.getSource() == disable) {
      gwtScheduler.setEnable(false);
    } else if (event.getSource() == enable) {
      gwtScheduler.setEnable(true);
    }
  }
}
