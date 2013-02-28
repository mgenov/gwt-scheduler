package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnClickedEvent extends GwtEvent<ColumnClickedEventHandler> {
  public static Type<ColumnClickedEventHandler> TYPE = new Type<ColumnClickedEventHandler>();
  private CalendarColumn column;

  public ColumnClickedEvent(CalendarColumn column) {
    this.column = column;
  }

  public Type<ColumnClickedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnClickedEventHandler handler) {
    handler.onColumnTitleClicked(this);
  }

  public static Type<ColumnClickedEventHandler> getTYPE() {
    return TYPE;
  }

  public CalendarColumn getColumn() {
    return column;
  }
}
