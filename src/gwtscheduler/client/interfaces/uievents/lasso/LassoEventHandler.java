package gwtscheduler.client.interfaces.uievents.lasso;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface LassoEventHandler extends EventHandler {

  /**
   * @param event
   */
  void onStartSelection(LassoStartSelectionEvent event);

  /**
   * @param event
   */
  void onEndSelection(LassoEndSelectionEvent event);

  /**
   * @param event
   */
  void onCancelSelection(LassoCancelSelectionEvent event);

  /**
   * @param event
   */
  void onUpdateSelection(LassoUpdateSelectionEvent event);
}
