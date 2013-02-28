package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class ColumnClickEvent extends GwtEvent<ColumnClickEventHandler> {
  public static Type<ColumnClickEventHandler> TYPE = new Type<ColumnClickEventHandler>();

  private String title;
  private int columnIndex;

  public ColumnClickEvent(String title, int columnIndex) {
    this.title = title;
    this.columnIndex = columnIndex;
  }

  public Type<ColumnClickEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(ColumnClickEventHandler handler) {
    handler.onColumnClick(this);
  }

  public static Type<ColumnClickEventHandler> getTYPE() {
    return TYPE;
  }

  public String getTitle() {
    return title;
  }

  public int getColumnIndex() {
    return columnIndex;
  }
}
