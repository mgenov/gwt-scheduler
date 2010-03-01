package gwtscheduler.client.widgets.common.decorator;

import com.google.gwt.user.client.Element;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.widgets.common.Cell;
import org.apache.tomcat.util.buf.ByteChunk;
import org.goda.time.DateTime;
import org.goda.time.Days;
import org.goda.time.Interval;
import org.goda.time.Period;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class MonthTitleProvider implements ColumnTitleProvider{
  private Interval interval;


  public MonthTitleProvider() {
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  @Override
  public String[] getColumns(int columnCount) {
    DateTime start = interval.getStart();
    int days = interval.toPeriod().toStandardDays().getDays();
    int increment = columnCount / days;

    assert days > 0 : "Number of days should not be <= 0";
    assert increment > 0 : "Increment should not be zero.";

    String columns[] = new String[columnCount];
    for (int i = 0; i < columns.length; i++) {
      columns[0] = start.dayOfWeek().getAsShortText();
      start = start.plusDays(increment);
    }
    return columns;
  }
}
