package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;

import com.google.gwt.dom.client.Style.Position;
import dragndrop.client.core.*;
import gwtscheduler.client.widgets.view.common.resize.*;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.CalendarEventView;

/**
 * This class is responsible for displaying events.
 *
 * @author malp
 */
public class EventsDashboardView extends AbstractGridOverlay implements EventsDashboard.Display, HasCalendarEventResizeStartHandlers, HasCalendarEventResizeEndHandlers, HasCalendarEventResizeHandlers {

  /**
   * Default constructor.
   */
  public EventsDashboardView() {
    // why this need to be set when AbstractGridOverlay is an absolute panel ???
    getElement().getStyle().setPosition(Position.ABSOLUTE);
//    widgetResizeHandler = new  EventsDashboardResizeHandler(this,)
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
    return new int[]{position[0] + leftTop[0], position[1] + leftTop[1]};
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

  @Override
  public int getRowDistance(int start, int end) {
    return getCellHeight() * (end - start);
  }

  @Override
  public HasCalendarEventResizeEndHandlers getHasCalendarEventResizeEndHandlers() {
    return this;
  }

  @Override
  public HasCalendarEventResizeStartHandlers getHasCalendarEventResizeStartHandlers() {
    return this;
  }


  @Override
  public HasCalendarEventResizeHandlers getHasCalendarEventResizeHandlers() {
    return this;
  }

  @Override
  public int getRowCount() {
    return grid.getRowNum();
  }

  @Override
  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeEndHandler handler) {
    return addHandler(handler, CalendarEventResizeEndEvent.TYPE);
  }

  @Override
  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeStartHandler handler) {
    return addHandler(handler, CalendarEventResizeStartEvent.TYPE);
  }

  @Override
  public HandlerRegistration addEventResizeEndHandler(CalendarEventResizeHandler handler) {
    return addHandler(handler, CalendarEventResizeEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDropHandler(DropHandler handler) {
    return addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragInHandler(DragInHandler handler) {
    return addHandler(handler, DragInEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragOutHandler(DragOutHandler handler) {
    return addHandler(handler, DragOutEvent.TYPE);
  }

  @Override
  public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
    return addHandler(handler, DragOverEvent.TYPE);
  }
}
