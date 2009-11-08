package gwtscheduler.client.utils;

import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.goda.time.ReadableDateTime;

import com.google.inject.Inject;

/**
 * Generic date factory. Used to calculate the correct interval for a given type
 * of calendar.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 * @see IntervalType
 */
public class GenericDateGenerator implements DateGenerator {

  /** interval start and end */
  private DateTime current;
  /** inner generator */
  private FixedDateGenerator generator;

  @Inject
  private AppConfiguration config;

  /**
   * Default constructor.
   */
  public GenericDateGenerator() {
  }

  public DateTime current() {
    return current;
  }

  public void init(IntervalType interval, ReadableDateTime start) {
    this.current = new DateTime(start.getMillis());

    if (IntervalType.DAY.equals(interval)) {
      generator = new DayDateGenerator();
    } else if (IntervalType.WEEK.equals(interval)) {
      generator = new WeekDateGenerator();
    } else if (IntervalType.MONTH.equals(interval)) {
      generator = new MonthDateGenerator();
    } else {
      throw new IllegalArgumentException("Unknown interval type: " + interval.toString());
    }
    goTo(start.toDateTime());
  }

  public void goTo(DateTime start) {
    generator.goTo(start);
  }

  public DateGenerator next() {
    generator.next();
    return this;
  }

  public DateGenerator previous() {
    generator.previous();
    return this;
  }

  public Interval interval() {
    return generator.interval();
  }

  /**
   * Utility interface that defines a fixed date generator.
   * @author malp
   */
  private interface FixedDateGenerator {
    /**
     * Advances the date.
     */
    void next();

    /**
     * Moves to the specified date.
     * @param start the date
     */
    void goTo(DateTime start);

    /**
     * Goes backward.
     */
    void previous();

    /**
     * Returns the current interval.
     * @return the current interval
     */
    Interval interval();
  }

  /**
   * Date generator for days.
   * @author malp
   */
  private class DayDateGenerator implements FixedDateGenerator {

    public Interval interval() {
      DateTime end = current.plusDays(1);
      return new Interval(current, end);
    }

    public void goTo(DateTime where) {
      current = where;
    }

    public void next() {
      current = current.plusDays(1);
    }

    public void previous() {
      current = current.plusDays(-1);
    }

  }
  /**
   * Date generator for weeks.
   * @author malp
   */
  private class WeekDateGenerator implements FixedDateGenerator {
    /** defines the number of days in a week */
    final int WeekSize;

    /**
     * Default constructor.
     */
    private WeekDateGenerator() {
      WeekSize = AppInjector.GIN.getInjector().getConfiguration().daysInWeek();
    }

    public void goTo(DateTime where) {
      current = where;
    }

    public Interval interval() {
      DateTime end = null;
      //adjust to day of week start
      while (current.getDayOfWeek() != config.startDayOfWeek()) {
        current = current.plusDays(-1);
      }
      end = current.plusDays(WeekSize);
      return new Interval(current, end);
    }

    public void next() {
      current = current.plusDays(WeekSize);
    }

    public void previous() {
      current = current.plusDays(-WeekSize);
    }

  }

  /**
   * Date generator for months.
   * @author malp
   */
  private class MonthDateGenerator implements FixedDateGenerator {

    public void goTo(DateTime where) {
      MutableDateTime mtd = new MutableDateTime(where.getMillis(),where.getChronology());
      mtd.setDayOfMonth(1);
      current = mtd.toDateTime();
    }

    public Interval interval() {
      DateTime end = null;
//      MutableDateTime monthStart = current.toMutableDateTime();
      DateTime iterator = new DateTime(current.getMillis(),current.getChronology()); 
//      monthStart.setDayOfMonth(1);
      end = current.toDateTime().plusMonths(1);

      //adjust start date so that the first week contains the start day 
      while (iterator.isAfter(current)) {//current always points to first day of month
        iterator = iterator.plusDays(-1);
      }
      while (iterator.getDayOfWeek() != config.startDayOfWeek()) {
        iterator = iterator.plusDays(-1);
      }
      //adjust end so that last day contains last day of month
      while (end.getDayOfWeek() != config.startDayOfWeek()) {
        end = end.plusDays(1);
      }
      end = end.plusDays(-1);//it ends right before the next start of week
      return new Interval(iterator, end);
    }

    public void next() {
      moveStart(1);
    }

    public void previous() {
      moveStart(-1);
    }

    /**
     * Utility method that advances the start date pointer a given amount.
     * @param months the number of months to move. Can be negative
     */
    private void moveStart(int months) {
//      Days d = Days.daysBetween(current, current.plusMonths(months));
//      int days = d.getDays();
//      current = current.plusDays(days / 2);
      current = current.plusMonths(months);
//      System.out.println("Start: " + current);
    }
  }

}
