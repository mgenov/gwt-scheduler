package gwtscheduler.client.interfaces;

import gwtscheduler.client.widgets.view.common.EventWidget;

/**
 * @author malp
 *
 */
public interface LassoElementFactory {

  /**
   * Creates an event widget
   * @param subject the subject
   * @param from the from position
   * @param to the to position
   * @return
   */
  EventWidget createLassoElement(LassoSubject subject, int[] from, int[] to);

}
