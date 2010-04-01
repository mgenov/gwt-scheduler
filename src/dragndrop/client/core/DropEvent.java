package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * This event is fired when user drop frame over the drop zone. This event care information about source widget(widget
 * who is registered for draggable. Can be null), object who is dropped over drop zone, mouse coordinates from where drag starts,
 * and mouse coordinates where objects is dropped.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class DropEvent extends GwtEvent<DropHandler> {
  public static final GwtEvent.Type<DropHandler> TYPE = new GwtEvent.Type<DropHandler>();

  private final Widget source;
  private final Object object;
  private final int[] dragStart; // contains two fields. 0 - startX mouse position, 1 - startY mouse position.
  private final int[] dragEnd;   // contains two fields. 0 - endX mouse position, 1 - endY mouse position.

  /**
   * Constructor.
   *
   * @param source widget.
   * @param object to be dropped.
   * @param dragStart drag start position. contains two fields. 0 - startX mouse position, 1 - startY mouse position.
   * @param dragEnd drag end position. contains two fields. 0 - endX mouse position, 1 - endY mouse position.
   */
  public DropEvent(Widget source, Object object, int[] dragStart, int[] dragEnd) {
    this.source = source;
    this.object = object;
    this.dragStart = dragStart;
    this.dragEnd = dragEnd;
  }

  @Override
  public Type<DropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DropHandler handler) {
    handler.onDrop(this);
  }

  /**
   * Get dropped object. Object that is dropped over drop zone.
   *
   * @return dropped object.
   */
  public Object getDroppedObject(){
    return object;
  }

  /**
   * Get source of dragged widget.
   *
   * @return source widget.
   */
  public Widget getSourceWidget(){
    return source;
  }

  /**
   * Get mouse position where drag stops and where object is dropped.
   * Contains two fields:
   * <pre>
   * 0 - endX mouse position
   * 1 - endY mouse position.
   * </pre>
   * @return int array containing two fields.
   */
  public int[] getEndPosition(){
    return dragEnd;
  }

  /**
   * Get mouse X position where drag stops and where object is dropped.
   *
   * @return int of mouse X position.
   */
  public int getEndX() {
    return dragEnd[0];
  }

  /**
   * Get mouse Y position where drag stops and where object is dropped.
   *
   * @return int of mouse Y position.
   */
  public int getEndY() {
    return dragEnd[1];
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
  public int[] getStartPosition(){
    return dragStart;
  }

  /**
   * Get mouse X position where drag start.
   *
   * @return int of mouse X position.
   */
  public int getStartX() {
    return dragStart[0];
  }

  /**
   * Get mouse Y position where drag start.
   *
   * @return int of mouse Y position.
   */
  public int getStartY() {
    return dragStart[1];
  }

}
