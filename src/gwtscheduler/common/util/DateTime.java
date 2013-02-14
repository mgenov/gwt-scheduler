package gwtscheduler.common.util;

import java.util.Date;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@SuppressWarnings({"deprecation"})
public final class DateTime {
  private static final int ONE_DAY_IN_SECONDS = 86400;
  private static final long ONE_HOUR_IN_SECONDS = 3600;
  private Date date;

  /**
   * the first day of week (usually 0 - Sunday)
   */

  public DateTime() {
    date = new Date();
  }

  public DateTime(Date date) {
    this.date = date;
  }

  public DateTime(long time) {
    this.date = new Date(time);
  }

  public DateTime(DateTime time) {
    this.date = new Date(time.getMillis());
  }

  public DateTime plusMonths(int months) {
//    return new DateTime(new Date(date.getTime() + (days * ONE_DAY_IN_SECONDS)));
    return new DateTime(new DateHelper(date).addMonths(months));
  }

  public void setSeconds(int seconds) {
    date.setSeconds(seconds);
  }

  public void setMinutes(int minutes) {
    date.setMinutes(minutes);
  }

  public void setHours(int hours) {
    date.setHours(hours);
  }

  public DateTime toDateTime() {
    return new DateTime(new Date(date.getTime()));
  }

  public long getMillis() {
    return date.getTime();
  }


  public int getMinuteOfDay() {
    return date.getHours() * 60 + date.getMinutes();
  }

  public DateTime plusMinutes(int minutes) {
    Date v = new Date(date.getTime());
    v.setMinutes(date.getMinutes() + minutes);
    return new DateTime(v.getTime());
  }

  public int getDayOfWeek() {
    return new DateHelper(date).getDayOfWeek();
  }

  public DateTime dayOfMonth(int day) {
    return new DateTime(new Date(date.getYear(), date.getMonth(), day));
  }

  public DateTime plusDays(int days) {
    return new DateTime(new Date(date.getYear(), date.getMonth(), date.getDate() + days, date.getHours(), date.getMinutes(), date.getSeconds()));
  }

  public boolean isAfter(DateTime current) {
    return date.after(current.date);
  }

  public DateTime trimToStart() {
    return new DateTime(new DateHelper(date).trim());
  }

  public DateTime trimToEnd() {
    return new DateTime(new DateHelper(date).trimToEnd());
  }

  public DateTime plusHours(int hours) {
    return new DateTime(date.getTime() + (hours * ONE_HOUR_IN_SECONDS * 1000));
  }

  public int hourOfDay() {
    return date.getHours();
  }

  public void setMillis(long mills) {
    date = new Date(mills);
  }

  public int getYear() {
    return 1900 + date.getYear();
  }

  public int getMonthOfYear() {
    return new DateHelper(date).getMonth();
  }

  public int getDayOfMonth() {
    return new DateHelper(date).getDay();
  }

  public DateTime addDuration(Duration duration) {
    return new DateTime(new Date(duration.getMills() + date.getTime()));
  }

  public DateTime plusMills(long mills) {
    return new DateTime(new Date(mills + date.getTime()));
  }

  public int getMinutes() {
    return date.getMinutes();
  }

  public Date asDate() {
    return date;
  }

  @Override
  public String toString() {
    return date.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DateTime)) return false;

    DateTime dateTime = (DateTime) o;

    if (date != null ? !date.equals(dateTime.date) : dateTime.date != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return date != null ? date.hashCode() : 0;
  }

  public DateTime setMillisOfMinute(int mills) {
    long time = (date.getTime() / 1000) *1000 + mills;
    return new DateTime(time);
  }
}
