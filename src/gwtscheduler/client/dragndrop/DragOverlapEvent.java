package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOverlapEvent extends GwtEvent<DragOverlapHandler> {
  public static final GwtEvent.Type<DragOverlapHandler> TYPE = new GwtEvent.Type<DragOverlapHandler>();
  private Widget frame;
  private int mouseX;
  private int mouseY;

  public DragOverlapEvent(Widget frame, int mouseX, int mouseY) {
    this.frame = frame;
    this.mouseX = mouseX;
    this.mouseY = mouseY;
  }

  @Override
  public Type<DragOverlapHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragOverlapHandler dragOverlapHandler) {
    dragOverlapHandler.onDragOverlap(this);
  }

  public void fire(Widget dropZone) {
    if (TYPE != null) {
      dropZone.fireEvent(this);
    }
  }

  public Widget getFrame() {
    return frame;
  }

  public void setFrame(Widget frame) {
    this.frame = frame;
  }

  public int getMouseX() {
    return mouseX;
  }

  public void setMouseX(int mouseX) {
    this.mouseX = mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

  public void setMouseY(int mouseY) {
    this.mouseY = mouseY;
  }
}
