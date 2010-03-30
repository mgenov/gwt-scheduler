package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Fired when dragging start. This event is fired to the handler that is attached to the drag zone. Event is not fired
 * to any drop zone.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragStartEvent extends GwtEvent<DragStartHandler>{
  public static Type<DragStartHandler> TYPE = new Type<DragStartHandler>();
  private final int[] dragStartPosition; // contains two fields. 0 - startX mouse position, 1 - startY mouse position.
  private final Frame frame;
  private final Object dropObject;

  /**
   * Constructor.
   *
   * @param dragStartPosition drag start position. contains two fields. 0 - startX mouse position, 1 - startY mouse position.
   * @param frame currently used frame.
   * @param dropObject dropped object.
   */
  DragStartEvent(int[] dragStartPosition, Frame frame, Object dropObject) {
    this.dragStartPosition = dragStartPosition;
    this.frame = frame;
    this.dropObject = dropObject;
  }

  @Override
  public Type<DragStartHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragStartHandler handler) {
    handler.onDragStart(this);
  }

  /**
   * Get mouse position where drag starts.
   * Contains two fields:
   * <pre>
   * 0 - startX mouse position
   * 1 - startY mouse position.
   * </pre>
   *
   * @return int array containing two fields.
   */
  public int[] getDragStartPosition() {
    return dragStartPosition;
  }

  /**
   * Get mouse X position where drag start.
   *
   * @return int of mouse X position.
   */
  public int getStartX() {
    return dragStartPosition[0];
  }

  /**
   * Get mouse Y position where drag start.
   *
   * @return int of mouse Y position.
   */
  public int getStartY() {
    return dragStartPosition[1];
  }

  /**
   * Get frame that is currently dragged over drag zone.
   *
   * @return frame that is dragged over drag zone.
   */
  public Frame getFrame() {
    return frame;
  }

  /**
   * Get object who will be dropped when dragging stops.
   *
   * @return drop object.
   */
  public Object getDropObject() {
    return dropObject;
  }
}
