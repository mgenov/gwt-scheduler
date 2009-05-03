package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.widgets.view.common.AbstractDayPanel;

/**
 * View class for weeks.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class WeekPanel extends AbstractDayPanel {

  /**
   * Default constructor.
   */
  public WeekPanel() {
    super();
  }

  @Override
  protected int getColumns() {
    return 7;
  }

  @Override
  protected int getRows() {
    return 48; // 24*2
  }

}
