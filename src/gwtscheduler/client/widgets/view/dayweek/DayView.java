package gwtscheduler.client.widgets.view.dayweek;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.Cell;

import java.util.List;

/**
 * Inner class for days calendar.
 * @author malp
 */
public class DayView extends AbstractDaysView {

  @Override
  protected AbstractDaysPanel buildDaysPanel() {
    return new DayPanel();
  }

  @Override
  protected int getColumnsSize() {
    return 1;
  }

  /**
   * Inner class for days panel.
   * @author malp
   */
  private static class DayPanel extends AbstractDaysPanel {

    @Override
    protected int getColumns() {
      return 1;
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