package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.common.util.DateTime;


/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class MoveObjectEvent extends GwtEvent<MoveObjectHandler> {
  public static Type<MoveObjectHandler> TYPE = new Type<MoveObjectHandler>();
  private final int[] oldCell;
  private final int[] newCell;
  private final DateTime oldTime;
  private final DateTime newTime;
  private final Object droppedObject;

  public MoveObjectEvent(int[] oldCell, int[] newCell, DateTime oldTime, DateTime newTime, Object droppedObject) {
    this.oldCell = oldCell;
    this.newCell = newCell;
    this.oldTime = oldTime;
    this.newTime = newTime;
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

  public DateTime getOldTime() {
    return oldTime;
  }

  public DateTime getNewTime() {
    return newTime;
  }
}
