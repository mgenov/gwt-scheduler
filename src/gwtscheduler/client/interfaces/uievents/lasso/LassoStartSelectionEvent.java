package gwtscheduler.client.interfaces.uievents.lasso;

/**
 * Resize event for resize aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class LassoStartSelectionEvent extends AbstractLassoEvent {

  /**
   * @see AbstractLassoEvent#AbstractLassoEvent(int, int)
   */
  public LassoStartSelectionEvent(int row, int col) {
    super(row, col);
  }

  @Override
  protected void dispatch(LassoEventHandler handler) {
    handler.onStartSelection(this);
  }

}
