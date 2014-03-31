package gwtscheduler.client.widgets.view.calendarevent;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnTitleOverEvent extends GwtEvent<ColumnTitleOverEventHandler> {
  public static Type<ColumnTitleOverEventHandler> TYPE = new Type<ColumnTitleOverEventHandler>();
  private CalendarColumn column;
  private final int left;
  private final int top;

  public ColumnTitleOverEvent(CalendarColumn column, int left, int top) {
    this.column = column;
    this.left = left;
    this.top = top;
  }

  public Type<ColumnTitleOverEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnTitleOverEventHandler handler) {
    handler.onOver(this);
  }

  public CalendarColumn getColumn() {
    return column;
  }

  public int getLeft() {
    return left;
  }

  public int getTop() {
    return top;
  }
}
