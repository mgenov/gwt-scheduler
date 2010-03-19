package gwtscheduler.client;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.events.TeamTaskEvent;
import gwtscheduler.client.modules.views.MainView;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeEndHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.Event;

import java.util.List;


/**
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
    presenter.deleteColumn(column);
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

  public void addEvent(TeamTaskEvent event) {
    CalendarPresenter presenter = presenters.get(selectedPresenter);
    presenter.addCalendarEvent(event);
  }

  public void addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
     for (CalendarPresenter calendar : presenters) {
      calendar.addEventResizeEndHandler(handler);
    }
  }

  public void addEventResizeStartHandler(CalendarEventResizeStartHandler handler) {
    for (CalendarPresenter presenter : presenters) {
      presenter.addEventResizeStartHandler(handler);
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
}
