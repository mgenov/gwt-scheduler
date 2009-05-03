package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.widgets.view.common.AbstractCompositeDaysPanel;
import gwtscheduler.client.widgets.view.common.AbstractDayPanel;

/**
 * Composite class for day views.
 */
public class CompositeDayPanel extends AbstractCompositeDaysPanel {

  @Override
  protected AbstractDayPanel createDayView() {
    return new DayPanel();
  }

}
