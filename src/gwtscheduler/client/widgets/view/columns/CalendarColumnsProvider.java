package gwtscheduler.client.widgets.view.columns;

import org.goda.time.Interval;

import java.util.List;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CalendarColumnsProvider {
  List<CalendarColumn> getColumns();

  void updateColumns(Interval interval, List<CalendarColumn> columns);
}
