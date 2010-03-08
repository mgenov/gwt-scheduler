package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DragOutEvent extends GwtEvent<DragOutHandler>{
  public static final GwtEvent.Type<DragOutHandler> TYPE = new GwtEvent.Type<DragOutHandler>();
  private Widget frame;

  public DragOutEvent(Widget frame) {
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

  public Widget getFrame() {
    return frame;
  }

  public void setFrame(Widget frame) {
    this.frame = frame;
  }

}
