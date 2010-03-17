package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.dom.client.*;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperImpl implements CalendarEventResizeHelper{
  public interface Display {
    HasMouseMoveHandlers getMouseMoveHandlers();

    HasMouseUpHandlers getMouseUpHandlers();

    void release();

    void go(EventsDashboard.Display dashboard);

    void go(EventsDashboard.Display dashboard, Integer left, Integer top);

    void capture();

    void setWidth(int width);

    void setHeight(int height);

    int getHeight();

  }

  private final EventsDashboard.Display eventsDisplay;
  private final DateGenerator dateGenerator;
  private final Display display;
  private CalendarEvent calendarEvent;
  private int[] startRow;
  private int[] endRow;
  private int eventHeight;

  public CalendarEventResizeHelperImpl(CalendarEvent calendarEvent, EventsDashboard.Display eventsDisplay, DateGenerator dateGenerator, Display display) {
    this.eventsDisplay = eventsDisplay;
    this.dateGenerator = dateGenerator;
    this.calendarEvent = calendarEvent;
    this.display = display;
    calendarEvent.getMouseDownHandlers().addMouseDownHandler(new MouseDownHandler(){
      @Override
      public void onMouseDown(MouseDownEvent event) {
        mouseDown(event);
      }
    });
    display.getMouseMoveHandlers().addMouseMoveHandler(new MouseMoveHandler(){
      @Override
      public void onMouseMove(MouseMoveEvent event) {
        mouseMove(event);
      }
    });
    display.getMouseUpHandlers().addMouseUpHandler(new MouseUpHandler(){
      @Override
      public void onMouseUp(MouseUpEvent event) {
        mouseUp(event);
      }
    });
  }

  void mouseDown(MouseDownEvent event) {
    int eventLeft = calendarEvent.getPosition().getLeft();
    int eventTop = calendarEvent.getPosition().getTop();
    display.go(eventsDisplay, eventLeft, eventTop);
    eventHeight = calendarEvent.getHeight();

    display.setWidth(calendarEvent.getWidth());
    display.setHeight(eventHeight);

    display.capture();

    startRow = eventsDisplay.getCellPosition(event.getClientX(), event.getClientY());

    CalendarEventResizeStartEvent resizeEvent = new CalendarEventResizeStartEvent(calendarEvent);
    eventsDisplay.getHasCalendarEventResizeEndHandlers().fireEvent(resizeEvent);
  }

  void mouseMove(MouseMoveEvent event) {
    int[] row = eventsDisplay.getCellPosition(event.getClientX(), event.getClientY());
    if(startRow == row || row[0] < 0){
      return;
    }
    endRow = row;
    int height = eventsDisplay.getRowDistance(startRow[0], row[0]);
    display.setHeight(eventHeight + height);
//    calendarEvent.setHeight(eventHeight + height);
  }

  void mouseUp(MouseUpEvent event) {
    display.release();
    display.go(eventsDisplay);
    if(startRow == endRow || (endRow[0] - startRow[0]) == 0){
      return;
    }
    Instant startTime = calendarEvent.getInterval().getStart().toInstant();
    Instant endTime = dateGenerator.getIntervalForRange(startRow, endRow, 48).getEnd().toInstant(); // TODO: 48 is hard codded.

    CalendarEventResizeEndEvent resizeEvent = new CalendarEventResizeEndEvent(calendarEvent, startTime, endTime);
    eventsDisplay.getHasCalendarEventResizeEndHandlers().fireEvent(resizeEvent);
  }

  private void fireOnResizeEvent(){
    CalendarEventResizeEvent resizeEvent = new CalendarEventResizeEvent();
    eventsDisplay.getHasCalendarEventResizeHandlers().fireEvent(resizeEvent);
  }

}
