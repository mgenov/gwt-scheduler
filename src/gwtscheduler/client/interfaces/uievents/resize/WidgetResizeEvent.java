package gwtscheduler.client.interfaces.uievents.resize;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WidgetResizeEvent extends GwtEvent<WidgetResizeHandler> {

  /** resize width */
  public final int width;
  /** resize height */
  public final int height;

  /**
   * Event type for blur events. Represents the meta-data associated with this event.
   */
  private static final Type<WidgetResizeHandler> TYPE = new Type<WidgetResizeHandler>();

  /**
   * Gets the event type associated with resize events.
   * @return the handler type
   */
  public static Type<WidgetResizeHandler> getType() {
    return TYPE;
  }

  @Override
  protected void dispatch(WidgetResizeHandler handler) {
    handler.onResize(this);
  }

  @Override
  public Type<WidgetResizeHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Main constructor.
   * @param availableWidth the available width
   * @param availableHeight available height
   */
  public WidgetResizeEvent(int availableWidth, int availableHeight) {
    this.width = availableWidth;
    this.height = availableHeight;
  }

  /**
   * Creates a new event.
   * @param dimension an array with the width and height
   */
  public WidgetResizeEvent(int[] dimension) {
    this.width = dimension[0];
    this.height = dimension[1];
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + height;
    result = prime * result + width;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    WidgetResizeEvent other = (WidgetResizeEvent) obj;
    if (height != other.height) {
      return false;
    }
    if (width != other.width) {
      return false;
    }
    return true;
  }
}
