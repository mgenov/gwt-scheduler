package gwtscheduler.client.dragndrop;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * This event is fired up when widget is dropped over drop zone.
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class DropEvent extends GwtEvent<DropHandler> {
  public static final GwtEvent.Type<DropHandler> TYPE = new GwtEvent.Type<DropHandler>();

  private Widget source;
  private Object object;
  private int mouseX;
  private int mouseY;

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

  public void fire(Widget dropZone) {
    if (TYPE != null) {
      dropZone.fireEvent(this);
    }
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
