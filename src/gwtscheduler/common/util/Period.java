package gwtscheduler.common.util;


import com.google.gwt.user.datepicker.client.CalendarUtil;

import java.util.Date;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Period {
  private DateTime start;
  private DateTime end;

  public Period(DateTime start, DateTime end) {
    this.start = start;
    this.end = end;
  }

  public Period(Date start, Date end) {
    this.start = new DateTime(start);
    this.end = new DateTime(end);
  }

  public DateTime getStart() {
    return start;
  }

  public DateTime getEnd() {
    return end;
  }

  public long getStartMillis() {
    return start.getMillis();
  }

  public long getEndMillis() {
    return end.getMillis();
  }

  public int getDays() {
    return CalendarUtil.getDaysBetween(start.asDate(),end.asDate());
  }

  public void setStart(DateTime start) {
    this.start = start;
  }

  public void setEnd(DateTime end) {
    this.end = end;
  }
}
