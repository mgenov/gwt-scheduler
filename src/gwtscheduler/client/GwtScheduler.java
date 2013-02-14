package gwtscheduler.client;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.modules.views.SchedulerMainView;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import gwtscheduler.common.util.DateTime;

import java.util.Date;

/**
 * Represents a scheduler that can consist different calendars.
 * <p/>
 * Example:
 * <p></p>
 * <p>1. Getting ana instance;</p>
 * <p/>
 * <pre>
 * CalendarSchedulerBuilder schedulerBuilder = new CalendarSchedulerBuilder();
 * <p></p>
 * GwtScheduler scheduler = schedulerBuilder.weekColumnScheduler(new TestAppConfiguration(), null).named("Team 1 Week Calendar").build();
 * </pre>
 * <pre>
 * GwtScheduler scheduler = schedulerBuilder.multiColumnScheduler(new TestAppConfiguration(), testteamsProvider, null).named("Teams").build();
 * </pre>
 * Where TestAppConfiguration is implementation of a AppConfiguration interface
 * and testteamsProvider is an instance of a custom implementation of a CalendarColumnsProvider intervace
 * scheduler now is an instance that consist a calendar that represents week days or multi-column calendar.
 * <p></p>
 * <p>2. Scheduler  navigation </p>
 * <pre>datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
 *        @Override
 *        public void onValueChange(ValueChangeEvent<Date> event) {
 *            Date date = event.getValue();
 *            main.navigateToDate(date);
 *            }
 *            });</pre>
 * <p></p>
 * <p> 3.Using the scheduler </p>
 * <p> The scheduler fires events for every activity that is involved in. This fired events can be easily handled by adding handlers to the scheduler</p>
 *
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler implements SchedulerMainView {
  public interface Display {
    void addCalendarDisplay(CalendarPresenter.Display display);
  }

  private CalendarPresenter presenter;
  private Display display;


  public GwtScheduler(CalendarPresenter presenter) {
    this.presenter = presenter;
  }

  public void bindDisplay(Display display) {
    this.display = display;
    presenter.setEnable(true);
    display.addCalendarDisplay(presenter.getDisplay());
  }

  @Override
  public Widget asWidget() {
    return (Widget) display;
  }

  @Override
  public void forceLayout() {
    presenter.forceLayout();
  }

  public void navigateToDate(Date date) {
//    Long mills = date.getTime();
    DateTime selectedDate = new DateTime(date);
//    DateTime selectedDate = new DateTime(mills, Constants.timeZone);
//    selectedDate.setHours(0);
//    selectedDate.setMinutes(0);
//    selectedDate.setSeconds(0);
//    selectedDate.setMillis(0);
    presenter.navigateToDateTime(selectedDate.trimToStart());
  }

  public void deleteColumn(CalendarColumn column) {
    presenter.removeColumn(column);
  }

  public void addColumn(CalendarColumn column) {
    presenter.addColumn(column);
  }

  public void addCalendarDropHandler(CalendarDropHandler handler) {
    presenter.addCalendarDropHandler(handler);
  }

  public void addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler) {
    presenter.addCalendarObjectMoveHandler(handler);
  }

  public void addEvent(Event event) {
    presenter.addCalendarEvent(event);
  }

  public void addEventDurationIntervalUpdateHandler(CalendarEventDurationChangeHandler handler) {
    presenter.addEventDurationChangeHandler(handler);
  }

  public void addEventResizeStartHandler(CalendarEventDurationChangeStartHandler handler) {
    presenter.addEventDurationChangeStartHandler(handler);
  }

  public void addEventDeleteEventHandler(EventDeleteEventHandler handler) {
    presenter.addEventDeleteEventHandler(handler);
  }

  public void deleteEvent(Event event) {
    presenter.deleteEvent(event);
  }

  public void updateEvent(Event event) {
    // TODO: its not good idea to update only on active calendar.
    presenter.updateEvent(event);
  }

  public void setEnable(boolean enable) {
    presenter.setEnable(enable);
  }

  public void addEventClickHandler(EventClickHandler handler) {
    presenter.addEventClickHandler(handler);
  }
}
