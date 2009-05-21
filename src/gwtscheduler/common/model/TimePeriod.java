package gwtscheduler.common.model;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

public class TimePeriod implements ITimePeriod {

  protected IDate start, end;
  protected long milis;

  /**
   * Default constructor.
   */
  public TimePeriod(IDate start, IDate end) {
    this.start = start;
    this.end = end;
    this.milis = end.millis() - start.millis();
  }

  public int hours() {
    return (int) (milis / (1000 * 3600));
  }

  public int days() {
    return (int) (milis / (1000 * 3600 * 24));
  }

  public int months() {
    // this is not quite correct...
    return (int) (milis / (1000 * 3600 * 24 * 31));
  }
}
