package gwtscheduler.client.widgets.view.weekcolumns;

import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.client.widgets.view.columns.CalendarColumnsProvider;
import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.Period;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a default week calendar columns provider. It is used when we instantiate calendar that
 * represents days in the week. 
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class WeekDaysColumnsProvider implements CalendarColumnsProvider {
  private static final int WEEK_SIZE = 7;

  private DateGenerator dateGenerator;
  private List<CalendarColumn> dayColumns;

  /**
   * Default Constructor
   * @param dateGenerator used to generate the current week interval
   */
  public WeekDaysColumnsProvider(DateGenerator dateGenerator) {
    this.dateGenerator = dateGenerator;

    Interval interval = this.dateGenerator.interval();
    int days = interval.toPeriod().toStandardDays().getDays();
    Period period = new Period(0, 0, 0, days, 0, 0, 0, 0);
    DateTime start = interval.getStart();

    dayColumns = generateWeekDayColumns(start, period);

  }

  /**
   * Generates a list of Columns for each day of the week.
   * @param start the start day as Daytime
   * @param period the Period of the interval
   * @return array list of calendar columns
   */
  private ArrayList<CalendarColumn> generateWeekDayColumns(DateTime start, Period period) {
    ArrayList<CalendarColumn> dayColumns = new ArrayList<CalendarColumn>();

    for (int i = 0; i < WEEK_SIZE; i++) {
      DayColumn column = new DayColumn( start.plusDays(i));
      dayColumns.add(column);
    }
    
    return dayColumns;
  }

  @Override
  public List<CalendarColumn> getColumns() {
    return this.dayColumns;
  }

  /**
   * Updates the date that is related with every column by the given interval.
   * @param interval the interval for which we want to update the columns
   * @param columns the list of columns
   */
  @Override
  public void updateColumns(Interval interval, List<CalendarColumn> columns) {
    DateTime current = interval.getStart();
    for (CalendarColumn column : columns) {
      column.setObject(current);
      current = current.plusDays(1);
    }
  }
}