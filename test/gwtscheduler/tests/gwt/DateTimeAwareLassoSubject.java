package gwtscheduler.tests.gwt;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;

import java.util.List;

import org.goda.time.DateTime;
import org.goda.time.Instant;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableInterval;

import com.google.gwt.user.client.Element;

/**
 * Utilit class for lasso tests.
 * @author malp
 */
public class DateTimeAwareLassoSubject implements LassoSubject {

  final int rows, cols;

  static Interval Interval;

  static {
    DateTime dt1 = new DateTime(2009, 01, 01, 01, 01, 01, 01);
    DateTime dt2 = new DateTime(2009, 01, 31, 01, 01, 01, 01);
    Interval = new Interval(dt1, dt2);
  }

  /**
   * Creates a new mock lasso subject, with a 100px x 100px grid.
   * @param rows the number of rows
   * @param cols the number of cols
   */
  public DateTimeAwareLassoSubject(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  @Override
  public int getColNum() {
    return cols;
  }

  @Override
  public List<Cell<Element>> getLassoSubjects() {
    return null;
  }

  @Override
  public int getRowNum() {
    return rows;
  }

  @Override
  public int getWidth() {
    return 100;
  }

  @Override
  public int getHeight() {
    return 100;
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end);
    //ranges are closed on start and open on end
    //[start, end[
    // so a correction is needed
    MutableDateTime last = to.toMutableDateTime();
    last.addDays(1);
    Interval i = new Interval(from, last.toInstant());
    return i;
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    int distance = (start[0] * getColNum()) + start[1];
    ReadableInterval curr = Interval.toMutableInterval();
    MutableDateTime time = curr.getStart().toMutableDateTime();

    time.addDays(distance);
    return time.toInstant();
  }

}