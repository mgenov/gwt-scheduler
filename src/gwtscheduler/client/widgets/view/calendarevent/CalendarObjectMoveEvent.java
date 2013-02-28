package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.util.DateTime;

/**
 * Fired when something already attached to calendar is moved to another place. Contains information about Calendar type
 * where something is moved, calendar title, object that is moved, column from where something is moved, column to where
 * something is moved, old time for moved object and new time where object is dropped.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarObjectMoveEvent extends GwtEvent<CalendarObjectMoveHandler>{
  public static final Type<CalendarObjectMoveHandler> TYPE = new Type<CalendarObjectMoveHandler>();
  private final CalendarType calendarType;
  private final String calendarTitle;
  private final Object droppedObject;
  private final CalendarColumn oldColumn;
  private final DateTime oldTime;
  private final CalendarColumn newColumn;
  private final DateTime newTime;

  public CalendarObjectMoveEvent(CalendarType calendarType, String calendarTitle, Object droppedObject, CalendarColumn oldColumn, DateTime oldTime, CalendarColumn newColumn, DateTime newTime) {
    this.calendarType = calendarType;
    this.calendarTitle = calendarTitle;
    this.droppedObject = droppedObject;
    this.oldColumn = oldColumn;
    this.oldTime = oldTime;
    this.newColumn = newColumn;
    this.newTime = newTime;
  }

  @Override
  public Type<CalendarObjectMoveHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarObjectMoveHandler handler) {
    handler.onCalendarObjectMove(this);
  }

  public CalendarType getCalendarType() {
    return calendarType;
  }

  public String getCalendarTitle() {
    return calendarTitle;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }

  public CalendarColumn getOldColumn() {
    return oldColumn;
  }

  public long getOldTimeMills() {
    return oldTime.getMillis();
  }

  public CalendarColumn getNewColumn() {
    return newColumn;
  }

  public long getNewTimeMills() {
    return newTime.getMillis();
  }

  public long getDifference(){
    return newTime.getMillis() - oldTime.getMillis();
  }

  public DateTime getOldTime() {
    return oldTime;
  }

  public DateTime getNewTime() {
    return newTime;
  }
}
