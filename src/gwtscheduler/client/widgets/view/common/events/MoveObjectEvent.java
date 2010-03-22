package gwtscheduler.client.widgets.view.common.events;

import com.google.gwt.event.shared.GwtEvent;
import gwtscheduler.client.widgets.view.common.events.MoveObjectHandler;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class MoveObjectEvent extends GwtEvent<MoveObjectHandler> {
  public static Type<MoveObjectHandler> TYPE = new Type<MoveObjectHandler>();
  private final int[] oldCell;
  private final int[] newCell;
  private final Instant oldTime;
  private final Instant newTime;
  private final Object droppedObject;

  public MoveObjectEvent(int[] oldCell, int[] newCell, Instant oldTime, Instant newTime, Object droppedObject) {
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

  public Instant getOldTime() {
    return oldTime;
  }

  public Instant getNewTime() {
    return newTime;
  }
}
