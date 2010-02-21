package gwtscheduler.client.interfaces.uievents.lasso;

import gwtscheduler.client.interfaces.LassoSubject;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoEndSelectionEvent extends AbstractLassoEvent {

  /** row/column */
  public final int[] endCell;

  /**
   * @param subject
   * @param from
   * @param to
   */
  public LassoEndSelectionEvent(LassoSubject subject, int[] from, int[] to) {
    super(subject, from);
    endCell = to;
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onEndSelection(this);
  }

}
