package dragndrop.client.core;

import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class DragOutEvent extends GwtEvent<DragOutHandler>{
  public static final GwtEvent.Type<DragOutHandler> TYPE = new GwtEvent.Type<DragOutHandler>();
  private Frame frame;

  public DragOutEvent(Frame frame) {
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

  public Frame getFrame() {
    return frame;
  }

  public void setFrame(DragFrame frame) {
    this.frame = frame;
  }

}
