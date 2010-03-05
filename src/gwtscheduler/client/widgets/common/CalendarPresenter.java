package gwtscheduler.client.widgets.common;

import gwtscheduler.client.widgets.view.columns.ColumnPanel;
import org.goda.time.Instant;
import org.goda.time.Interval;

import gwtscheduler.client.widgets.common.navigation.EventNavigationListener;

import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a calendar controller. Responsible for mediating the view and the
 * listener. For most cases, the implementing class will be the listener itself.
 *
 * @author malp
 */
public interface CalendarPresenter {
  public interface Display extends GenericCalendarDisplay{

    void removeColumn();

    ColumnPanel.Display getMainPanel();
  }

  public void bindDisplay(Display display);

  public void setColNum(int columns);

  public void setTabLabel(String tabLabel);

  public Display getDisplay();

  /**
   * Gets the label for the view.
   *
   * @return the label
   */
  String getTitle();

  /**
   * Gets the navigation events listener.
   *
   * @return the listener
   */
  EventNavigationListener getNavigationListener();

  /**
   * Gets the widget.
   *
   * @return the widget
   */
  Widget getWidgetDisplay();

  /**
   * Forces the layout of the display.
   */
  void forceLayout();

  /**
   * Gets the correspondent time interval for a given cell range
   *
   * @param start the starting cell
   * @param end   the end cell
   * @return the time interval
   */
  Interval getIntervalForRange(int[] start, int[] end);

  /**
   * Gets the correspondent instant for a cell
   *
   * @param start the starting cell
   */
  Instant getInstantForCell(int[] start);

}
