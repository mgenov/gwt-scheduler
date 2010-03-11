package gwtscheduler.client;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.Instant;
import org.goda.time.Interval;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarDropEvent extends GwtEvent<CalendarDropHandler>{
  public static final Type<CalendarDropHandler> TYPE = new Type<CalendarDropHandler>();
  private final CalendarType calendarType;
  private final String calendarTitle;
  private final Object droppedObject;
  private final CalendarColumn calendarColumn;
  private final Instant dropTime;

  public CalendarDropEvent(CalendarType type, String title, Object object, CalendarColumn column, Instant time) {
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

  public CalendarType getCalendarType() {
    return calendarType;
  }

  public String getCalendarTitle() {
    return calendarTitle;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }

  public CalendarColumn getCalendarColumn() {
    return calendarColumn;
  }

  public Instant getDropTime() {
    return dropTime;
  }
}
