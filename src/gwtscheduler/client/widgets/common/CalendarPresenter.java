package gwtscheduler.client.widgets.common;

import com.google.gwt.event.shared.HandlerRegistration;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;
import gwtscheduler.client.widgets.view.calendarevent.EventDeleteEventHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventDurationIntervaUpdateHandler;
import gwtscheduler.client.widgets.view.common.resize.CalendarEventResizeStartHandler;
import gwtscheduler.common.event.Event;
import gwtscheduler.client.widgets.view.calendarevent.CalendarObjectMoveHandler;
import gwtscheduler.client.widgets.view.calendarevent.CalendarDropHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import gwtscheduler.common.event.EventClickHandler;
import org.goda.time.DateTime;
import org.goda.time.Instant;

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

//  /**
//   * Gets the correspondent time interval for a given cell range
//   *
//   * @param start the starting cell
//   * @param end the end cell
//   * @return the time interval
//   */
//  Interval getIntervalForRange(int[] start, int[] end);

//  /**
//   * Gets the correspondent instant for a cell
//   *
//   * @param start the starting cell
//   */
//  Instant getInstantForCell(int[] start);

  void deleteColumn(CalendarColumn column);

  void addColumn(CalendarColumn column);

  HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler);

  HandlerRegistration addCalendarObjectMoveHandler(CalendarObjectMoveHandler handler);

  void setCalendarType(CalendarType type);

  CalendarType getCalendarType();

  void addCalendarEvent(Event event);

  HandlerRegistration addEventResizeEndHandler(CalendarEventDurationIntervaUpdateHandler handler);

  HandlerRegistration addEventResizeStartHandler(CalendarEventResizeStartHandler handler);

  HandlerRegistration addEventDeleteEventHandler(EventDeleteEventHandler handler);

  void deleteEvent(Event event);
  
  void updateEvent(Event event);

  void navigateToDateTime(DateTime dateTime);

  void setEnable(boolean enable);
  
  void addEventClickHandler(EventClickHandler handler);

}
