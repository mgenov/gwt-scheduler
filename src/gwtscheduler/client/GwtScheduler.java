package gwtscheduler.client;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.views.MainView;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import org.goda.time.MutableDateTime;

import java.util.Date;
import java.util.List;


/**
 * Represents a scheduler that can consist different calendars.
 *
 * Example:
 *<p></p>
 * <p>1. Getting ana instance;</p>
 *
 * <pre>GwtScheduler scheduler = schedulerBuilder.addTab(new CalendarsBuilder().newMultiColumn(new TestAppConfiguration(), testteams1, null).named("Teams").build())
            .addTab(new CalendarsBuilder().newWeekColumn(new TestAppConfiguration(), null).named("Team 1 Week Calendar").build()).build();
 </pre>
 *
 * scheduler now is an instance that consist 2 calendars from differnt types.
 * <p></p>
 * <p>2. Scheduler  navigation </p>
 * <pre>datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      @Override
      public void onValueChange(ValueChangeEvent<Date> event) {
        Date date = event.getValue();
        main.navigateToDate(date);
      }
    });</pre>

 *<p></p>
 * <p> 3.Using the scheduler </p>
 * <p> The scheduler fires events for every activity that is involved in. This fired events can be easily handled by adding handlers to the scheduler</p>
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler implements MainView, BeforeSelectionHandler<Integer> {

  public interface Display {

    void selectTab(int i);

    void add(CalendarPresenter.Display display, String title);

    void addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler);

  }


  /**
   * presenters array
   */
  private List<CalendarPresenter> presenters;
  private Display display;
  private int selectedPresenter = 0;


  public GwtScheduler(List<CalendarPresenter> presenters) {
    this.presenters = presenters;
  }

  public void bindDisplay(Display display) {
    this.display = display;

    display.addBeforeSelectionHandler(this);

    for (CalendarPresenter presenter : presenters) {
      add(presenter);
      presenter.setEnable(true);
    }

  }


  @Override
  public Widget asWidget() {
    return (Widget) display;
  }

  @Override
  public void forceLayout() {
    for (CalendarPresenter p : presenters) {
      p.forceLayout();
    }
  }

  @Override
  public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
    CalendarPresenter presenter = presenters.get(event.getItem());
    presenter.forceLayout();
    selectedPresenter = event.getItem();
  }

  /**
   * Adds a new view to it's display panel.
   *
   * @param presenter the controller
   */
  private void add(CalendarPresenter presenter) {
    display.add(presenter.getDisplay(), presenter.getTitle());
  }

  /**
   * Selects a tab.
   *
   * @param i the tab index
   */
  public void selectTab(int i) {
    display.selectTab(i);
    selectedPresenter = i;
  }

  public void deleteColumn(CalendarColumn column) {
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.removeColumn(column);
  }

  public void addColumn(CalendarColumn column) {
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.addColumn(column);
  }

  public void addCalendarDropHandler(CalendarDropHandler handler) {
    for (CalendarPresenter calendar : presenters) {
      calendar.addCalendarDropHandler(handler);
    }
  }

  public void addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    for (CalendarPresenter calendar : presenters) {
      calendar.addCalendarObjectMoveHandler(handler);
    }
  }

  public void addEvent(Event event) {
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.addCalendarEvent(event);
  }

  public void addEventDurationIntervalUpdateHandler(CalendarEventDurationChangeHandler handler) {
     for (CalendarPresenter calendar : presenters) {
      calendar.addEventDurationChangeHandler(handler);
    }
  }

  public void addEventResizeStartHandler(CalendarEventDurationChangeStartHandler handler) {
    for (CalendarPresenter presenter : presenters) {
      presenter.addEventDurationChangeStartHandler(handler);
    }
  }
   public void addEventDeleteEventHandler(EventDeleteEventHandler handler) {
     for (CalendarPresenter calendar : presenters) {
      calendar.addEventDeleteEventHandler(handler);
    }
  }

  public void deleteEvent(Event event) {
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.deleteEvent(event);
  }
  
  public void updateEvent(Event event) {
    // TODO: its not good idea to update only on active calendar.
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.updateEvent(event);
  }

  public void setEnable(boolean enable){
    for (CalendarPresenter presenter : presenters) {
      presenter.setEnable(enable);
    }
  }

  public void navigateToDate(Date date) {

        Long mills = date.getTime();
        MutableDateTime selectedDate = new MutableDateTime(date.getTime());
        selectedDate.setHourOfDay(0);
        selectedDate.setMinuteOfHour(0);
        selectedDate.setMinuteOfHour(0);
        selectedDate.setMillisOfSecond(0);
//        eventBus.fireEvent(new NavigateToEvent(selectedDate.toDateTime()));
        CalendarPresenter presenter = presenters.get(selectedPresenter);
        presenter.navigateToDateTime(selectedDate.toDateTime());

  }

  public void addEventClickHandler(EventClickHandler handler) {
    for (CalendarPresenter presenter : presenters) {
      presenter.addEventClickHandler(handler);
    }
  }
  
}
