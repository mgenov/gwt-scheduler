package gwtscheduler.client.widgets.view.dayweek;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.Cell;

import java.util.List;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class WeekView extends AbstractDaysView {

  @Override
  protected AbstractDaysPanel buildDaysPanel() {
    return new WeekPanel();
  }

  @Override
  protected int getColumnsSize() {
    return 7;
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

  @Override
  public List<Cell<Element>> getDecorableElements() {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }
}