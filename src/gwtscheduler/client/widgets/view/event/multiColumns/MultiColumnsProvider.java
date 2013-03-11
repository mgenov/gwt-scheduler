package gwtscheduler.client.widgets.view.event.multiColumns;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import gwtscheduler.common.util.Period;

import java.util.List;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class MultiColumnsProvider implements CalendarColumnsProvider{

  private List<CalendarColumn> columns;

  public MultiColumnsProvider(List<CalendarColumn> columns) {
    this.columns = columns;
  }

  @Override
  public List<CalendarColumn> getColumns() {
    return columns;
  }

  @Override
  public void updateColumns(Period interval, List<CalendarColumn> columns) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
