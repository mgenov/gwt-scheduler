package gwtscheduler.client.widgets.common.decorator;

import org.goda.time.Interval;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface ColumnTitleProvider {
  String[] getColumns(int columnCount);

  void setInterval(Interval interval);
}
