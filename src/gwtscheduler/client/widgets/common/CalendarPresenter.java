package gwtscheduler.client.widgets.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.CalendarChangeHandler;
import gwtscheduler.client.CalendarDropHandler;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.HasCalendarChangeHandlers;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarContent;
import gwtscheduler.client.widgets.view.columns.CalendarHeader;
import gwtscheduler.client.widgets.view.columns.HasCalendarDropHandlers;
import org.goda.time.Instant;
import org.goda.time.Interval;

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

    HasCalendarDropHandlers getHasCalendarDropHandlers();

    HasCalendarChangeHandlers getHasCalendarChangeHandlers();
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
   * Gets the correspondent time interval for a given cell range
   *
   * @param start the starting cell
   * @param end the end cell
   * @return the time interval
   */
  Interval getIntervalForRange(int[] start, int[] end);

//  /**
//   * Gets the correspondent instant for a cell
//   *
//   * @param start the starting cell
//   */
//  Instant getInstantForCell(int[] start);

  void deleteColumn(CalendarColumn column);

  void addColumn(CalendarColumn column);

  HandlerRegistration addCalendarDropHandler(CalendarDropHandler handler);

  HandlerRegistration addCalendarChangeHandler(CalendarChangeHandler handler);

  void setCalendarType(CalendarType type);

  CalendarType getCalendarType();

}
