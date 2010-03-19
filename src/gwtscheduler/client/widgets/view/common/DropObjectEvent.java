package gwtscheduler.client.widgets.view.common;

import com.google.gwt.event.shared.GwtEvent;
import org.goda.time.Instant;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DropObjectEvent extends GwtEvent<DropObjectHandler>{
  public static Type<DropObjectHandler> TYPE = new Type<DropObjectHandler>();
  private final int[] newCell;
  private final Instant newTime;
  private final Object droppedObject;

  public DropObjectEvent(int[] newCell, Instant newTime, Object droppedObject) {
    this.newCell = newCell;
    this.newTime = newTime;
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

  public Instant getNewTime() {
    return newTime;
  }
}
