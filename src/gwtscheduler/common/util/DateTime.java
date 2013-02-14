package gwtscheduler.common.util;

import com.google.gwt.user.datepicker.client.CalendarUtil;

import java.util.Date;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
@SuppressWarnings({"deprecation"})
public final class DateTime {
  private static final int ONE_DAY_IN_SECONDS = 86400;
  private Date date;

  /**
   * the first day of week (usually 0 - Sunday)
   */
  private int firstDayOfWeek = 1;


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

  public DateTime plusMonths(int days) {
    return new DateTime(new Date(date.getTime() + days * ONE_DAY_IN_SECONDS));
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

  public DateTime plus(Duration duration) {

    return null;
  }

  public int getMinuteOfDay() {
    return date.getHours() * 60 + date.getMinutes();
  }

  public DateTime plusDays(int days) {
    Date incrementedDate = new Date(date.getTime());

    CalendarUtil.addDaysToDate(incrementedDate, days);

    return new DateTime(incrementedDate.getTime());
  }

  public void add(Duration duration) {

  }

  public DateTime plusMinutes(int minutes) {
    Date v = new Date(date.getTime());
    v.setMinutes(date.getMinutes() + minutes);
    return new DateTime(v.getTime());
  }

  public int getDayOfWeek() {
    int day = date.getDay() - firstDayOfWeek;
    if (day < 0) {
      day = 7 + day;
    }
    return day;
  }

  public DateTime dayOfMonth(int day) {
    return new DateTime(new Date(date.getYear(), date.getMonth(), day));
  }

  public DateTime addDays(int days) {
    return new DateTime(
            new Date(date.getYear(), date.getMonth(), date.getDate() + days, date.getHours(), date.getMinutes(), date.getSeconds())
    );
  }

  public boolean isAfter(DateTime current) {
    return date.after(current.date);
  }
}
