package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOutEvent extends GwtEvent<DragOutHandler>{
  public static final GwtEvent.Type<DragOutHandler> TYPE = new GwtEvent.Type<DragOutHandler>();
  private DragFrame frame;

  public DragOutEvent(DragFrame frame) {
    this.frame = frame;
  }


  @Override
  public Type<DragOutHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DragOutHandler dragOutHandler) {
    dragOutHandler.onDragUnOverlap(this);
  }

  public DragFrame getFrame() {
    return frame;
  }

  public void setFrame(DragFrame frame) {
    this.frame = frame;
  }

}
