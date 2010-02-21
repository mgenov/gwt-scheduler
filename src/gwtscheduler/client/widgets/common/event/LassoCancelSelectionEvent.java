package gwtscheduler.client.widgets.common.event;

import gwtscheduler.client.widgets.common.ComplexGrid;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoCancelSelectionEvent extends AbstractLassoEvent {

  /**
   * @see AbstractLassoEvent#AbstractLassoEvent(ComplexGrid, int[])
   */
  public LassoCancelSelectionEvent(ComplexGrid subject, int[] pos) {
    super(subject, pos);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onCancelSelection(this);
  }

}
