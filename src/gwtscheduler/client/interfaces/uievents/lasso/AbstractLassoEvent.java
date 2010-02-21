package gwtscheduler.client.interfaces.uievents.lasso;

import gwtscheduler.client.interfaces.LassoSubject;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public abstract class AbstractLassoEvent extends GwtEvent<LassoEventHandler> {

  /** row/column */
  public final int[] cell;
  /** subject */
  public final LassoSubject subject;

  /**
   * Event type for lasso events. Represents the meta-data associated with this
   * event.
   */
  private static final Type<LassoEventHandler> TYPE = new Type<LassoEventHandler>();

  /**
   * Gets the event type associated with resize events.
   * @return the handler type
   */
  public static Type<LassoEventHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<LassoEventHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Main constructor.
   * @param pos the x/y coordinates
   */
  public AbstractLassoEvent(LassoSubject subject, int[] pos) {
    this.subject = subject;
    this.cell = pos;
  }
}
