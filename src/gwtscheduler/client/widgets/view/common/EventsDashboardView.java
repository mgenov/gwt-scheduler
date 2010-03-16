package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;

import com.google.gwt.dom.client.Style.Position;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.CalendarEventView;
import gwtscheduler.common.event.EventPosition;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventsDashboardView extends AbstractGridOverlay implements EventsDashboard.Display {

  /**
   * Default constructor.
   */
  public EventsDashboardView() {

    // why this need to be set when AbstractGridOverlay is an absolute panel ???
    getElement().getStyle().setPosition(Position.ABSOLUTE);

    //TODO: Remove this event after layout is completed
    CalendarEventView event = new CalendarEventView();
    event.getElement().getStyle().setZIndex(33);
    add(event, 50,50);    
  }

  @Override
  public AbsolutePanel asWidget() {
    return this;
  }

  @Override
  public int[] getCellPosition(int x, int y) {
    return calculateCellPosition(x, y);
  }

  public int[] getWindowCellPosition(int[] cell) {
    int[] leftTop = calculateLeftTop(cell);
    int[] position = getAbsolutePosition();
    return new int[] {position[0]+leftTop[0], position[1]+leftTop[1]};
  }

  @Override
  public int[] calculateLeftTop(int[] cellPos) {
    return super.calculateLeftTop(cellPos);
  }

  @Override
  public CalendarEvent.Display getCalendarEventDisplay() {
    return new CalendarEventView();
  }

  @Override
  public int getCellWidth() {
    return grid.getWidth() / grid.getColNum();
  }

  @Override
  public int getCellHeight() {
    return grid.getHeight() / grid.getRowNum();
  }

  // TODO: write method who calculate height distance between two cells
}
