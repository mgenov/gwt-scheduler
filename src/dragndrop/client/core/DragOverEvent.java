package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Event is fired all time when user drag frame over drag zone. Event contain data for current mouse position, drag start
 * position, DragZone who fire the event and object that will be dropped after drag finished over drop zone.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOverEvent extends GwtEvent<DragOverHandler> {
  public static final Type<DragOverHandler> TYPE = new Type<DragOverHandler>();
  private final int[] currentPosition;    // contains two fields. 0 - current mouse X position, 1 - current mouse Y position.
  private final int[] dragStartPosition;  // contains two fields. 0 - startX mouse position, 1 - startY mouse position.
  private final Object dropObject;
  private final DragZone dragZone;
  private final Frame frame;

  /**
   * Constructor.
   *
   * @param dragZone who wire this event.
   * @param currentPosition current mouse position. contains two fields. 0 - current mouse X position, 1 - current mouse Y position.
   * @param dragStartPosition drag start position. contains two fields. 0 - startX mouse position, 1 - startY mouse position.
   * @param dropObject object to be dropped when drag finish.
   * @param frame currently used frame.
   */
  DragOverEvent(DragZone dragZone, int[] currentPosition, int[] dragStartPosition, Object dropObject, Frame frame) {
    this.dragZone = dragZone;
    this.currentPosition = currentPosition;
    this.dragStartPosition = dragStartPosition;
    this.dropObject = dropObject;
    this.frame = frame;
  }

  @Override
  public Type<DragOverHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragOverHandler dragOverHandler) {
    dragOverHandler.onDragOver(this);
  }

  /**
   * Get current mouse position.
   * Contains two fields:
   * <pre>
   * 0 - current mouse X position.
   * 1 - current mouse Y position.
   * </pre>
   *
   * @return int array containing two fields.
   */
  public int[] getCurrentMousePosition() {
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
}
