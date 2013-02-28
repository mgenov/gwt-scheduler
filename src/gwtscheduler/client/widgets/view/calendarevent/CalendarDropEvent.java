package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.CalendarType;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.util.DateTime;

/**
 * Fired when something is dropped over calendar. Contains information about calendar type, Calendar title, dropped object,
 * column where something is dropped and time on the place where something is dropped.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarDropEvent extends GwtEvent<CalendarDropHandler>{
  public static final Type<CalendarDropHandler> TYPE = new Type<CalendarDropHandler>();
  private final CalendarType calendarType;
  private final String calendarTitle;
  private final Object droppedObject;
  private final CalendarColumn calendarColumn;
  private final DateTime dropTime;

  public CalendarDropEvent(CalendarType type, String title, Object object, CalendarColumn column, DateTime time) {
    this.calendarType = type;
    this.calendarTitle = title;
    this.droppedObject = object;
    this.calendarColumn = column;
    this.dropTime = time;
  }

  @Override
  public Type<CalendarDropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarDropHandler handler) {
    handler.onCalendarDrop(this);
  }

  /**
   * Get calendar type.
   * @return CalendarType object;
   */
  public CalendarType getCalendarType() {
    return calendarType;
  }

  /**
   * Get calendar title.
   * @return calendar title.
   */
  public String getCalendarTitle() {
    return calendarTitle;
  }

  /**
   * Get dropped object.
   * @return dropped object.
   */
  public Object getDroppedObject() {
    return droppedObject;
  }

  public CalendarColumn getCalendarColumn() {
    return calendarColumn;
  }

  public long getDropTimeMills() {
    return dropTime.getMillis();
  }

  public DateTime getDropTime() {
    return dropTime;
  }

}
