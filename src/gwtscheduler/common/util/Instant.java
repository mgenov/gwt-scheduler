package gwtscheduler.common.util;

import java.util.Date;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Instant {

  private final Date date;

  public Instant(long time) {
    this.date = new Date(time);
  }

  public DateTime toDateTime() {
    return new DateTime(date.getTime());
  }
}
