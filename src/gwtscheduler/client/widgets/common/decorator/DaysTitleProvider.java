package gwtscheduler.client.widgets.common.decorator;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.Period;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DaysTitleProvider implements ColumnTitleProvider {
  private DateTime start;
  private Period p;
  private Interval interval;


  public DaysTitleProvider() {
  }

  public DaysTitleProvider(DateTime start, Period p) {
    this.start = start;
    this.p = p;
  }

  public DaysTitleProvider(Interval interval) {
   setInterval(interval);
  }

  @Override
  public String[] getColumns(int columnCount) {

    int days = p.toStandardDays().getDays();
    int increment = columnCount / days;

    assert days > 0 : "Number of days should not be <= 0";
    assert increment > 0 : "Increment should not be zero.";

    String columns[] = new String[columnCount];
    for (int i = 0; i < columns.length; i++) {
      String wd = start.dayOfWeek().getAsShortText();
      String month = start.monthOfYear().getAsShortText();
      String day = start.dayOfMonth().getAsShortText();

      columns[i] = wd + ", " + month + " " + day;
      start = start.plusDays(increment);
    }
    return columns;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
    start = interval.getStart();
    int days = interval.toPeriod().toStandardDays().getDays();
    p = new Period(0, 0, 0, days, 0, 0, 0, 0);
  }
}