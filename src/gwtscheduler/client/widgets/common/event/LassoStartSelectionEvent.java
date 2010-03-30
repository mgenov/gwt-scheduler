package gwtscheduler.client.widgets.common.event;

import gwtscheduler.client.widgets.common.ComplexGrid;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoStartSelectionEvent extends AbstractLassoEvent {

  public LassoStartSelectionEvent(ComplexGrid subject, int[] pos) {
    super(subject, pos);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onStartSelection(this);
  }

}
