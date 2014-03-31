package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnTitleMouseOverEvent extends GwtEvent<ColumnTitleMouseOverEventHandler> {
  public static Type<ColumnTitleMouseOverEventHandler> TYPE = new Type<ColumnTitleMouseOverEventHandler>();
  private String title;
  private int columnIndex;
  private int left;
  private int top;

  public ColumnTitleMouseOverEvent(String title, int columnIndex, int left, int top) {
    this.title = title;
    this.columnIndex = columnIndex;
    this.left = left;
    this.top = top;
  }

  public Type<ColumnTitleMouseOverEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnTitleMouseOverEventHandler handler) {
    handler.onMouseOver(this);
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  public String getTitle() {
    return title;
  }

  public int getLeft() {
    return left;
  }

  public int getTop() {
    return top;
  }
}
