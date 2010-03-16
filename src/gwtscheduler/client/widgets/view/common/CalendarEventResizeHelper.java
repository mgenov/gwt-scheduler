package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import gwtscheduler.common.event.CalendarEvent;

import java.util.Map;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarEventResizeHelper implements MouseDownHandler, MouseMoveHandler, MouseUpHandler{
  private CalendarEvent calendarEvent;
  private EventsDashboard.Display display;
  private int startRow[];
  private int eventHeight;
  private Label label = new Label();

  public CalendarEventResizeHelper(CalendarEvent calendarEvent, EventsDashboard.Display display) {
    this.calendarEvent = calendarEvent;
    this.display = display;
    calendarEvent.getMouseDownHandlers().addMouseDownHandler(this);
    label.addMouseMoveHandler(this);
    label.addMouseUpHandler(this);
  }
  
  @Override
  public void onMouseUp(MouseUpEvent event) {
    DOM.releaseCapture(label.getElement());
    display.asWidget().remove(label);
  }

  @Override
  public void onMouseDown(MouseDownEvent event) {
    display.asWidget().add(label, calendarEvent.getPosition().getLeft(), calendarEvent.getPosition().getTop());
    DOM.setCapture(label.getElement());
    startRow = display.getCellPosition(event.getClientX(), event.getClientY());
    eventHeight = calendarEvent.getHeight();
  }

  @Override
  public void onMouseMove(MouseMoveEvent event) {
    int[] row = display.getCellPosition(event.getClientX(), event.getClientY());
    if(startRow == row || row[0] < 0){
      return;
    }
    int height = display.getRowDistance(startRow[0], row[0]);
    calendarEvent.setHeight(eventHeight + height);
  }
}
