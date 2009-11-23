package gwtscheduler.client.widgets.view.dayweek;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.utils.lasso.VerticalLassoStrategy;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class WeekView extends AbstractDaysView {

  @Override
  protected AbstractDaysPanel createDaysPanel() {
    return new WeekPanel();
  }

  @Override
  protected LassoStrategy getStrategy() {
    //XXX: move this to presenter
    return new VerticalLassoStrategy(false);
  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class WeekPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 7;
    }

    @Override
    protected int getRows() {
      return AppInjector.GIN.getInjector().getConfiguration().rowsInDay();
    }
  }
}