package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOverEvent extends GwtEvent<DragOverHandler>{ 
  public static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();
  private final Frame frame;
  private final int mouseX;
  private final int mouseY;
  private final int dragZoneTop;
  private final int dragZoneLeft;
  private final Object dropObject;

  public DragOverEvent(Frame frame, int mouseX, int mouseY, int dragZoneTop, int dragZoneLeft, Object object) {
    this.frame = frame;
    this.mouseX = mouseX;
    this.mouseY = mouseY;
    this.dragZoneTop = dragZoneTop;
    this.dragZoneLeft = dragZoneLeft;
    this.dropObject = object;
  }

  @Override
  public Type<DragOverHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragOverHandler dragOverHandler) {
    dragOverHandler.onDragOver(this);
  }

  public Frame getFrame() {
    return frame;
  }


  public int getMouseX() {
    return mouseX;
  }


  public int getMouseY() {
    return mouseY;
  }

  public int getDragZoneLeft() {
    return dragZoneLeft;
  }

  public int getDragZoneTop() {
    return dragZoneTop;
  }

  public Object getDropObject() {
    return dropObject;
  }
}
