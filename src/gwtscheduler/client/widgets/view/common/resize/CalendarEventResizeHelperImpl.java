package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperImpl implements CalendarEventResizeHelper {
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

    void setCursorStyle(String style);
  }

  private final EventsDashboard.Display eventsDisplay;
  private final DateGenerator dateGenerator;
  private final Display display;
  private final CalendarEvent calendarEvent;
  private int[] eventStartRow;
  private int[] startRow;
  private int[] endRow = new int[2];
  private int eventHeight;

  public CalendarEventResizeHelperImpl(CalendarEvent calendarEvent, EventsDashboard.Display eventsDisplay, DateGenerator dateGenerator, Display display) {
    this.eventsDisplay = eventsDisplay;
    this.dateGenerator = dateGenerator;
    this.calendarEvent = calendarEvent;
    this.display = display;
    calendarEvent.getMouseDownHandlers().addMouseDownHandler(new MouseDownHandler() {
      @Override
      public void onMouseDown(MouseDownEvent event) {
        mouseDown(event);
      }
    });
    display.getMouseMoveHandlers().addMouseMoveHandler(new MouseMoveHandler() {
      @Override
      public void onMouseMove(MouseMoveEvent event) {
        mouseMove(event);
      }
    });
    display.getMouseUpHandlers().addMouseUpHandler(new MouseUpHandler() {
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

    eventStartRow = calendarEvent.getStartCellPosition();

    CalendarEventResizeStartEvent resizeEvent = new CalendarEventResizeStartEvent(calendarEvent);
    eventsDisplay.getHasCalendarEventResizeEndHandlers().fireEvent(resizeEvent);
  }

  void mouseMove(MouseMoveEvent event) {
    int[] row = eventsDisplay.getCellPosition(event.getClientX(), event.getClientY());
    if (row[0] < 0 || endRow[0] == row[0]) {
      return;
    }

    endRow = row;

    Interval currentInterval = getFrameInterval();

    CalendarEventResizeEvent resizeEvent = new CalendarEventResizeEvent(currentInterval, this, calendarEvent);
    eventsDisplay.getHasCalendarEventResizeHandlers().fireEvent(resizeEvent);

    int height = eventsDisplay.getRowDistance(startRow[0], row[0]);
    display.setHeight(eventHeight + height);
  }

  void mouseUp(MouseUpEvent event) {
    display.release();
    display.go(eventsDisplay);
    if (startRow == endRow || (endRow[0] - startRow[0]) == 0) {
      return;
    }

    Interval frameInterval = getFrameInterval();

    CalendarEventResizeEndEvent resizeEvent = new CalendarEventResizeEndEvent(calendarEvent, frameInterval.getStart().toInstant(), frameInterval.getEnd().toInstant());
    eventsDisplay.getHasCalendarEventResizeEndHandlers().fireEvent(resizeEvent);
  }

  private Interval getFrameInterval() {
    Instant startTime = calendarEvent.getInterval().getStart().toInstant();
    Instant endTime = dateGenerator.getIntervalForRange(eventStartRow, endRow, 48).getEnd().toInstant();
    return new Interval(startTime, endTime);
  }

  @Override
  public void setCursorStyle(String style) {
    display.setCursorStyle(style);
  }
}
