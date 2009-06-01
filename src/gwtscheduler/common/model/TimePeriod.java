package gwtscheduler.common.model;

import gwtscheduler.common.calendar.IDate;
import gwtscheduler.common.calendar.ITimePeriod;

/**
 * Represents a time period.
 * @author malp
 */
public class TimePeriod implements ITimePeriod {

  /** date boundaries */
  protected IDate start, end;
  /** useful for truncating */
  protected long milis;

  /**
   * Default constructor.
   * @param start the start
   * @param end the end
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

  @Override
  public String toString() {
    return "from: " + start.toString() + " to: " + end.toString();
  }

  public IDate start() {
    return start.copy();
  }

  public IDate end() {
    return end.copy();
  }

}
