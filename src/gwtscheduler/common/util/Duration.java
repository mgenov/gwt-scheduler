package gwtscheduler.common.util;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class Duration {
  private long mills;

  public Duration(long mills) {
    this.mills = mills;
  }

  public Duration(int count, PeriodType periodType) {

    long index = 1;
    if(PeriodType.DAYS.equals(periodType)){
      index = 24 * 60 * 60 * 1000;
    }
    mills = count * index;
  }

  public long getMills() {
    return mills;
  }
}
