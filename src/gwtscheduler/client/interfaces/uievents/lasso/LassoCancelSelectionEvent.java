package gwtscheduler.client.interfaces.uievents.lasso;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoCancelSelectionEvent extends AbstractLassoEvent {

  /**
   * @see AbstractLassoEvent#AbstractLassoEvent(int, int)
   */
  public LassoCancelSelectionEvent(int row, int col) {
    super(row, col);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onCancelSelection(this);
  }

}
