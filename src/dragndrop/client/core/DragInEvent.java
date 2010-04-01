package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * This event is fired when dragged frame enter in drop zone. Event is fired and drop zone can react when dragged
 * frame entered over drop zone.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class DragInEvent extends GwtEvent<DragInHandler> {
  public static final GwtEvent.Type<DragInHandler> TYPE = new GwtEvent.Type<DragInHandler>();
  private final Frame frame;
  private final int[] currentPosition;    // contains two fields. 0 - current mouse X position, 1 - current mouse Y position.
  private final int[] dragStartPosition;  // contains two fields. 0 - startX mouse position, 1 - startY mouse position.
  private final Object dropObject;
  private final DragZone dragZone;

  /**
   * Constructor.
   *
   * @param frame currently dragged frame.
   * @param dragStartPosition drag start position. contains two fields. 0 - startX mouse position, 1 - startY mouse position.
   * @param currentPosition current mouse position. contains two fields. 0 - current mouse X position, 1 - current mouse Y position.
   * @param dropObject object to be dropped.
   * @param dragZone who fires this event.
   */
  public DragInEvent(Frame frame, int[] dragStartPosition, int[] currentPosition, Object dropObject, DragZone dragZone) {
    this.frame = frame;
    this.dragStartPosition = dragStartPosition;
    this.currentPosition = currentPosition;
    this.dropObject = dropObject;
    this.dragZone = dragZone;
  }

  @Override
  public Type<DragInHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragInHandler dragInHandler) {
    dragInHandler.onDragInEvent(this);
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
   * Get current mouse position.
   * Contains two fields:
   * <pre>
   * 0 - current mouse X position.
   * 1 - current mouse Y position.
   * </pre>
   * @return int array containing two fields.
   */
  public int[] getCurrentMousePosition(){
    return currentPosition;
  }

  /**
   * Get current mouse X position.
   *
   * @return int of mouse X position.
   */
  public int getMouseX() {
    return currentPosition[0];
  }

  /**
   * Get current mouse Y position.
   *
   * @return int of mouse Y position.
   */
  public int getMouseY() {
    return currentPosition[1];
  }

  /**
   * Get drag zone who fire the event.
   *
   * @return DragZone
   */
  public DragZone getDragZone() {
    return dragZone;
  }

  /**
   * Get object who will be dropped when dragging stops.
   *
   * @return drop object.
   */
  public Object getDropObject() {
    return dropObject;
  }

  /**
   * Get mouse position where drag starts.
   * Contains two fields:
   * <pre>
   * 0 - startX mouse position
   * 1 - startY mouse position.
   * </pre>
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

}
