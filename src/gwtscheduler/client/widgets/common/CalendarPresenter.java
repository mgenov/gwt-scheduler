package gwtscheduler.client.widgets.common;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationChangeStartHandler;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.client.widgets.view.event.EventClickHandler;
import gwtscheduler.common.util.DateTime;

/**
 * Defines a calendar controller. Responsible for mediating the view and the
 * listener. For most cases, the implementing class will be the listener itself.
 *
 * @author malp
 */
public interface CalendarPresenter {
  public interface Display extends GenericCalendarDisplay{

    CalendarHeader.Display getCalendarHeaderDisplay();

    CalendarContent.Display getCalendarContentDisplay();
    
    void addWidgetResizeHandler(WidgetResizeHandler handler);
  }

  public void bindDisplay(Display display);

  public void setTittle(String tabLabel);

  public Display getDisplay();

  /**
   * Gets the label for the view.
   *
   * @return the label
   */
  String getTitle();

  /**
   * Forces the layout of the display.
   */
  void forceLayout();

  /**
   * Remove given column from calendar.
   *
   * @param column to be removed.
   */
  void removeColumn(CalendarColumn column);

  /**
   * Add new column to the calendar.
   * 
   * @param column to be added.
   */
  void addColumn(CalendarColumn column);

  /**
   * Add handler who will catch CalendarDropEvent. This event is fired when something is dropped over calendar.
   * 
   * @param handler who will handle event.
   * @return object that is responsible for removing handler from handler manager.
   */
  HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler);

  /**
   * Add handler who will catch CalendarObjectMoveEvent. This event is fired when something is moved from one place to
   * another over the calendar.
   * 
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  HandlerRegistration addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler);

  /**
   * Sets calendar type.
   *
   * @param type CalendarType.
   */
  void setCalendarType(CalendarType type);

  /**
   * Return calendar type.
   * 
   * @return CalendarType.
   */
  CalendarType getCalendarType();

  /**
   * Add new event on the calendar. This event will be wrapped with CalendarEvent and will be placed on the calendar.
   *
   * @param event to be added to the calendar.
   */
  void addCalendarEvent(Event event);

  /**
   * Add handler for CalendarEventDurationChangeEvent. This event is fired when height of the CalendarEvent is changed.
   * This means when mouse is up. When resizing is finished.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  HandlerRegistration addEventDurationChangeHandler(CalendarEventDurationChangeHandler handler);

  /**
   * Add handler for CalendarEventDurationChangeStartEvent. This event is fired when someone click on CalendarEvent to
   * resize the event. This mean not resizing in the real time, just click on resize button.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  HandlerRegistration addEventDurationChangeStartHandler(CalendarEventDurationChangeStartHandler handler);

  /**
   * Add handler for EventDeleteEvent. This event is fired when someone close the event.
   *
   * @param handler who will handle the event.
   * @return object that is responsible for removing handler from handler manager.
   */
  HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler);

  /**
   * Delete event from calendar.
   *
   * @param event to be deleted.
   */
  void deleteEvent(Event event);

  /**
   * Update current event on the calendar.
   *
   * @param event event to be updated with new data that is carried with given event.
   */
  void updateEvent(Event event);

  void navigateToDateTime(DateTime dateTime);

  void setEnable(boolean enable);

  /**
   * Add handler for event that is fired when some one click on the CalendarEvent.
   *
   * @param handler who will handle the event.
   */
  void addEventClickHandler(EventClickHandler handler);

}
