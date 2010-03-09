package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * This event is fired when dragged frame enter in drop zone. Event is fired up and drop zone can react when dragged
 * frame entered over drop zone.
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragInEvent extends GwtEvent<DragInHandler> {
  public static final GwtEvent.Type<DragInHandler> TYPE = new GwtEvent.Type<DragInHandler>();
  private final DragFrame frame;
  private final int mouseX;
  private final int mouseY;

  /**
   * Constructor accept widget who is dragged, mouse x position and mouse y position.
   * @param frame dragged widget, not dragged frame.
   * @param mouseX mouse left position.
   * @param mouseY mouse top position.
   */
  public DragInEvent(DragFrame frame, int mouseX, int mouseY) {
    this.frame = frame;
    this.mouseX = mouseX;
    this.mouseY = mouseY;
  }

  @Override
  public Type<DragInHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragInHandler dragInHandler) {
    dragInHandler.onDragOverlap(this);
  }

  public DragFrame getFrame() {
    return frame;
  }

  public int getMouseX() {
    return mouseX;
  }

  public int getMouseY() {
    return mouseY;
  }

}
