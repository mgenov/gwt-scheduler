package gwtscheduler.client.widgets.view.common.resize;

import com.google.gwt.event.dom.client.*;
import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.common.EventIntervalCollisionException;
import gwtscheduler.client.widgets.view.common.EventsDashboard;
import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelperImpl implements CalendarEventResizeHelper {
  public interface Display {
    HasMouseMoveHandlers getMouseMoveHandlers();

    HasMouseUpHandlers getMouseUpHandlers();

    void release();

    void removeFromParent(EventsDashboard.Display dashboard);

    void go(EventsDashboard.Display dashboard, Integer left, Integer top);

    void capture();

    void setWidth(int width);

    void setHeight(int height);

    int getHeight();

    void setCursorStyle(String style);
  }

  private static final String EVENT_IN_COLLISION = "Resized event is in collision with another event!";
  private final EventsDashboard.Display eventsDisplay;
  private final DateGenerator dateGenerator;
  private final Display display;
  private final CalendarEvent calendarEvent;
  private EventBus calendarBus;
  private int[] eventStartRow;
  private int[] startRow;
  private int[] endRow = new int[2];
  private int eventHeight;
  private boolean collision = false;

  public CalendarEventResizeHelperImpl(CalendarEvent calendarEvent, EventsDashboard.Display eventsDisplay, DateGenerator dateGenerator, Display display, EventBus calendarBus) {
    this.eventsDisplay = eventsDisplay;
    this.dateGenerator = dateGenerator;
    this.calendarEvent = calendarEvent;
    this.display = display;
    this.calendarBus = calendarBus;

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

    CalendarEventDurationChangeStartEvent resizeEvent = new CalendarEventDurationChangeStartEvent(calendarEvent);
    calendarBus.fireEvent(resizeEvent);
  }

  void mouseMove(MouseMoveEvent event) {
    int[] row = eventsDisplay.getCellPosition(event.getClientX(), event.getClientY());
    if (eventStartRow[0] > row[0] || row[0] < 0 || endRow[0] == row[0]) {
      return;
    }

    endRow = row;

    Period currentInterval = getFrameInterval();

    CalendarEventResizeEvent resizeEvent = new CalendarEventResizeEvent(currentInterval, this, calendarEvent);
    calendarBus.fireEvent(resizeEvent);

    int height = eventsDisplay.getRowDistance(startRow[0], row[0]);
    display.setHeight(eventHeight + height);
  }

  void mouseUp(MouseUpEvent event) {
    display.release();
    display.removeFromParent(eventsDisplay);
    int[] row = eventsDisplay.getCellPosition(event.getClientX(), event.getClientY());
    if (startRow[0] == row[0] || startRow == endRow || (endRow[0] - startRow[0]) == 0) {
      return;
    }

    if(collision){
      throw new EventIntervalCollisionException(EVENT_IN_COLLISION);
    }

    Period frameInterval = getFrameInterval();

    CalendarEventDurationChangeEvent resizeEvent = new CalendarEventDurationChangeEvent(calendarEvent, frameInterval.getStart(), frameInterval.getEnd());
    calendarBus.fireEvent(resizeEvent);
  }

  private Period getFrameInterval() {
      DateTime startTime = calendarEvent.getInterval().getStart();
      DateTime endTime = dateGenerator.getIntervalForRange(eventStartRow, endRow, eventsDisplay.getRowCount()).getEnd();
      return new Period(startTime, endTime);
  }

  @Override
  public void setCursorStyle(String style) {
    display.setCursorStyle(style);
  }

  @Override
  public void setInCollision(boolean collision) {
    this.collision = collision;
  }
}
