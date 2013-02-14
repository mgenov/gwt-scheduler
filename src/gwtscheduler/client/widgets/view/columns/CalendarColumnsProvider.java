package gwtscheduler.client.widgets.view.columns;

import gwtscheduler.common.util.Period;

import java.util.List;

/**
 * Represents a columns provider that is responsible for providing the calendar columns.
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CalendarColumnsProvider {
  /**
   * gets a list of columns
   * @return list of CalendarColumns 
   */
  List<CalendarColumn> getColumns();

  /**
   * Updates the given columns. This method is used when a date was change from the navigation and we need to update
   *  a list of columns. For example When we have a week calendar, we need to set a new date on every column when
   * we move to another week.  
   *
   * @param interval the interval for which we want to update the columns
   * @param columns the list of columns
   */
  void updateColumns(Period interval, List<CalendarColumn> columns);
}
