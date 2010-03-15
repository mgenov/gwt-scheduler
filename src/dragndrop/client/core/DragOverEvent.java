package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOverEvent extends GwtEvent<DragOverHandler>{ 
  public static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();
  private final int mouseX;
  private final int mouseY;
  private final DragZone dragZone;

  public DragOverEvent(DragZone dragZone, int mouseX, int mouseY) {
    this.dragZone = dragZone;
    this.mouseX = mouseX;
    this.mouseY = mouseY;
  }

  @Override
  public Type<DragOverHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragOverHandler dragOverHandler) {
    dragOverHandler.onDragOver(this);
  }

  public int getMouseX() {
    return mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

  public DragZone getDragZone() {
    return dragZone;
  }
}
