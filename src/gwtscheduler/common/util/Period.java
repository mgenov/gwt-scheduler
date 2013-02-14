package gwtscheduler.common.util;


/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class Period {
  public Period(int hours, int minutes, int seconds, int milliseconds) {

  }

  public Period(int count, PeriodType days) {

  }

  public Duration toStandardDuration() {

//    long millis = getMillis();  // no overflow can happen, even with Integer.MAX_VALUEs
//    millis += (((long) getSeconds()) * ((long) DateTimeConstants.MILLIS_PER_SECOND));
//    millis += (((long) getMinutes()) * ((long) DateTimeConstants.MILLIS_PER_MINUTE));
//    millis += (((long) getHours()) * ((long) DateTimeConstants.MILLIS_PER_HOUR));
//    millis += (((long) getDays()) * ((long) DateTimeConstants.MILLIS_PER_DAY));
//    millis += (((long) getWeeks()) * ((long) DateTimeConstants.MILLIS_PER_WEEK));
//    return new Duration(millis);
      return null;
  }
}
