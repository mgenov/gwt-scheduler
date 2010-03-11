package gwtscheduler.client;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarChangeEvent extends GwtEvent<CalendarChangeHandler>{
  public static final Type<CalendarChangeHandler> TYPE = new Type<CalendarChangeHandler>();
  private final CalendarType calendarType;
  private final String calendarTitle;
  private final Object droppedObject;
  private final CalendarColumn oldColumn;
  private final Instant oldTime;
  private final CalendarColumn newColumn;
  private final Instant newTime;

  public CalendarChangeEvent(CalendarType calendarType, String calendarTitle, Object droppedObject, CalendarColumn oldColumn, Instant oldTime, CalendarColumn newColumn, Instant newTime) {
    this.calendarType = calendarType;
    this.calendarTitle = calendarTitle;
    this.droppedObject = droppedObject;
    this.oldColumn = oldColumn;
    this.oldTime = oldTime;
    this.newColumn = newColumn;
    this.newTime = newTime;
  }

  @Override
  public Type<CalendarChangeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarChangeHandler handler) {
    handler.onCalendarMove(this);
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

  public Instant getOldTime() {
    return oldTime;
  }

  public CalendarColumn getNewColumn() {
    return newColumn;
  }

  public Instant getNewTime() {
    return newTime;
  }
}
