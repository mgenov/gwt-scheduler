package gwtscheduler.client;

import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.modules.views.MainView;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import org.goda.time.MutableDateTime;

import java.util.Date;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtScheduler implements MainView {
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
    Long mills = date.getTime();
    MutableDateTime selectedDate = new MutableDateTime(mills);
    selectedDate.setHourOfDay(0);
    selectedDate.setMinuteOfHour(0);
    selectedDate.setMinuteOfHour(0);
    selectedDate.setMillisOfSecond(0);
    presenter.navigateToDateTime(selectedDate.toDateTime());
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
