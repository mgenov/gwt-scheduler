package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOverEvent extends GwtEvent<DragOverHandler>{
  public static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();
  private final Frame frame;
  private final int mouseX;
  private final int mouseY;

  public DragOverEvent(Frame frame, int mouseX, int mouseY) {
    this.frame = frame;
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

  public Frame getFrame() {
    return frame;
  }


  public int getMouseX() {
    return mouseX;
  }


  public int getMouseY() {
    return mouseY;
  }
}
