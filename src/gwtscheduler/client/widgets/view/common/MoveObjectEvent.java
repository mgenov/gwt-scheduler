package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class MoveObjectEvent extends GwtEvent<MoveObjectHandler> {
  public static Type<MoveObjectHandler> TYPE = new Type<MoveObjectHandler>();
  private final int[] oldCell;
  private final int[] newCell;
  private final Object droppedObject;

  public MoveObjectEvent(int[] oldCell, int[] newCell, Object droppedObject) {
    this.oldCell = oldCell;
    this.newCell = newCell;
    this.droppedObject = droppedObject;
  }

  @Override
  public Type<MoveObjectHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(MoveObjectHandler handler) {
    handler.onMoveObject(this);
  }

  public int[] getOldCell() {
    return oldCell;
  }

  public int[] getNewCell() {
    return newCell;
  }

  public Object getDroppedObject() {
    return droppedObject;
  }
}
