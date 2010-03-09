package gwtscheduler.client.widgets.view.columns;

import com.google.gwt.event.shared.GwtEvent;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CalendarDropEvent extends GwtEvent<CalendarDropHandler>{
  public static final Type<CalendarDropHandler> TYPE = new Type<CalendarDropHandler>();
  private final String oldColumnTitle;
  private final Instant oldTime;
  private final String columnTitle;
  private final Instant time;
  private final Object object;

  public CalendarDropEvent(String columnTitle, Instant time, String oldColumnTitle, Instant oldTime, Object object) {
    this.oldColumnTitle = oldColumnTitle;
    this.oldTime = oldTime;
    this.columnTitle = columnTitle;
    this.time = time;
    this.object = object;
  }

  @Override
  public Type<CalendarDropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CalendarDropHandler handler) {
    handler.onCalendarDrop(this);
  }

  public Instant getTime() {
    return time;
  }

  public Object getObject() {
    return object;
  }

  public String getOldColumnTitle() {
    return oldColumnTitle;
  }

  public Instant getOldTime() {
    return oldTime;
  }

  public String getColumnTitle() {
    return columnTitle;
  }
}
