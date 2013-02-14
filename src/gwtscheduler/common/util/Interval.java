package gwtscheduler.common.util;

import java.util.Date;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Interval {

  private DateTime start;
  private DateTime end;

  public Interval(DateTime start, DateTime end) {
    this.start = start;
    this.end = end;
  }

  public Interval(long start, long end) {
    this.start = new DateTime(new Date(start));
    this.end = new DateTime(new Date(end));
  }


  public long getStartMillis() {
    return start.getMillis();
  }

  public long getEndMillis() {
    return end.getMillis();
  }

  public DateTime getStart() {
    return new DateTime(start.getMillis());
  }
}
