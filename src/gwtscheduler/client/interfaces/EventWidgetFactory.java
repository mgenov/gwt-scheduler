package gwtscheduler.client.interfaces;

import gwtscheduler.client.widgets.view.common.EventWidget;

public interface EventWidgetFactory {

  /**
   * Creates an event widget
   * @param subject the subject
   * @param from the from position
   * @param to the to position
   * @return
   */
  EventWidget createEvent(LassoSubject subject, int[] from, int[] to);

}
