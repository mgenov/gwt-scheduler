package gwtscheduler.client.widgets.view.event;

import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Interval;

import java.util.Date;

/**
 * Represents a duration interval(period) of any activity for example
 * 
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class DurationInterval {
  private Date start;
  private Date end;

  public DurationInterval() {
  }

  public DurationInterval(Date start, Date end) {
    this.start = start;
    this.end = end;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public long getDurationInMills() {
    long duration = end.getTime() - start.getTime();
    return duration;
  }

  static Interval getInterval(Date start,Date end){
    return new Interval(new DateTime(start), new DateTime(end));
  }


  public static Interval getInterval(long start, long end) {
    return new Interval(new DateTime(start), new DateTime(end));
  }
}
