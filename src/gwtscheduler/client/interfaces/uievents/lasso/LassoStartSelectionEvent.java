package gwtscheduler.client.interfaces.uievents.lasso;

import gwtscheduler.client.interfaces.LassoSubject;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoStartSelectionEvent extends AbstractLassoEvent {

  public LassoStartSelectionEvent(LassoSubject subject, int[] pos) {
    super(subject, pos);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onStartSelection(this);
  }

}
