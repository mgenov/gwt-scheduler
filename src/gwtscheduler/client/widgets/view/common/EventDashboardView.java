package gwtscheduler.client.widgets.view.common;

import com.google.gwt.user.client.ui.AbsolutePanel;

import com.google.gwt.dom.client.Style.Position;
import gwtscheduler.common.event.CalendarEventView;

/**
 * This class is responsible for displaying events.
 * @author malp
 */
public class EventDashboardView extends AbstractGridOverlay implements EventsDashboard.Display {

  /**
   * Default constructor.
   */
  public EventDashboardView() {

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
}
