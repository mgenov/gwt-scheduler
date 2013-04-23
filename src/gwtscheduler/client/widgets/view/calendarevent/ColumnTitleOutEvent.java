package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnTitleOutEvent extends GwtEvent<ColumnTitleOutEventHandler> {
  public static Type<ColumnTitleOutEventHandler> TYPE = new Type<ColumnTitleOutEventHandler>();
  private CalendarColumn column;

  public ColumnTitleOutEvent(CalendarColumn column) {
    this.column = column;
  }

  public Type<ColumnTitleOutEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnTitleOutEventHandler handler) {
    handler.onOut(this);
  }

  public CalendarColumn getColumn() {
    return column;
  }
}
