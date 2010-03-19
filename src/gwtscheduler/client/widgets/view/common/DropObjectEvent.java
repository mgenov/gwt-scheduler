package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DropObjectEvent extends GwtEvent<DropObjectHandler>{
  public static Type<DropObjectHandler> TYPE = new Type<DropObjectHandler>();
  private final int[] newCell;
  private final Object droppedObject;

  public DropObjectEvent(int[] newCell, Object droppedObject) {
    this.newCell = newCell;
    this.droppedObject = droppedObject;
  }

  @Override
  public Type<DropObjectHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DropObjectHandler handler) {
    handler.onDropObject(this);
  }

  public int[] getNewCell() {
    return newCell;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }
}
