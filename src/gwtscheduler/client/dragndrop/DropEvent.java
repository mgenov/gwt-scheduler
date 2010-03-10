package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * This event is fired up when widget is dropped over drop zone.
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class DropEvent extends GwtEvent<DropHandler> {
  public static final GwtEvent.Type<DropHandler> TYPE = new GwtEvent.Type<DropHandler>();

  private final Widget source;
  private final Object object;
  private int startX;
  private int startY;
  private int endX;
  private int endY;

  public DropEvent(Widget source, Object object) {
    this.source = source;
    this.object = object;
  }

  @Override
  public Type<DropHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DropHandler handler) {
    handler.onDrop(this);
  }

  public Object getDroppedObject(){
    return object;
  }

  public Widget getSourceWidget(){
    return source;
  }

  public int getEndX() {
    return endX;
  }

  public void setEndX(int endX) {
    this.endX = endX;
  }

  public int getEndY() {
    return endY;
  }

  public void setEndY(int endY) {
    this.endY = endY;
  }

  public int getStartX() {
    return startX;
  }

  public void setStartX(int startX) {
    this.startX = startX;
  }

  public int getStartY() {
    return startY;
  }

  public void setStartY(int startY) {
    this.startY = startY;
  }
}
