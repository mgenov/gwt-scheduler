package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnTitleMouseOutEvent extends GwtEvent<ColumnTitleMouseOutEventHandler> {
  public static Type<ColumnTitleMouseOutEventHandler> TYPE = new Type<ColumnTitleMouseOutEventHandler>();
  private final String title;
  private final int columnIndex;

  public ColumnTitleMouseOutEvent(String title, int columnIndex) {
    this.title = title;
    this.columnIndex = columnIndex;
  }

  public Type<ColumnTitleMouseOutEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnTitleMouseOutEventHandler handler) {
    handler.onMouseOut(this);
  }

  public String getTitle() {
    return title;
  }

  public int getColumnIndex() {
    return columnIndex;
  }
}
