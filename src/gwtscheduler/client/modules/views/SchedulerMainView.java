package gwtscheduler.client.modules.views;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author malp
 */
public interface SchedulerMainView {

  /**
   * @return
   */
  Widget asWidget();

  /**
   * Forces the layout.
   */
  void forceLayout();
}
