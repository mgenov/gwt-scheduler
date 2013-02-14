package gwtscheduler.client.utils;

import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.common.calendar.IntervalType;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Duration;
import gwtscheduler.common.util.Period;
import gwtscheduler.common.util.PeriodType;


/**
 * Generic date factory. Used to calculate the correct interval for a given type
 * of calendar.
 *
 * @author Miguel Ping
 * @version $Revision: $
 * @see IntervalType
 * @since 1.0
 */
public class GenericDateGenerator implements DateGenerator {
  private static final int START_DAY_OF_WEEK = 2;
  private static final int WEEK_SIZE = 7;

  /**
   * interval start and end
   */
  private DateTime current;
  /**
   * inner generator
   */
  private FixedDateGenerator generator;
  private Period currentInterval;


  /**
   * Default constructor.
   */
  public GenericDateGenerator() {
  }

  public DateTime current() {
    return current;
  }

  public void init(IntervalType interval, DateTime start) {
    //TODO maybe use a flag|bitmask for resetting fields?
    DateTime mtd = new DateTime(start);
    mtd.setSeconds(0);
    mtd.setMinutes(0);
    mtd.setHours(0);

    this.current = mtd.toDateTime();

    if (IntervalType.DAY.equals(interval)) {
      generator = new DayDateGenerator();
    } else if (IntervalType.WEEK.equals(interval)) {
      generator = new WeekDateGenerator();
    } else if (IntervalType.MONTH.equals(interval)) {
      generator = new MonthDateGenerator();
    } else {
      throw new IllegalArgumentException("Unknown interval type: " + interval.toString());
    }
    goToDate(this.current.toDateTime());

    currentInterval = generator.interval();
  }

  public void goToDate(DateTime start) {
    generator.goTo(start.toDateTime());
  }

  public DateGenerator next() {
    generator.next();
    return this;
  }

  public DateGenerator previous() {
    generator.previous();
    return this;
  }

  public Period interval() {
    return generator.interval();
  }

  @Override
  public Period currentInterval() {
    generator.goTo(currentInterval.getStart());
    return currentInterval;
  }

  @Override
  public DateTime getInstantForCell(int[] cell, int rowNum) {
    return generator.getInstantForCell(cell,rowNum);  
  }

  @Override
  public Period getIntervalForRange(int[] start, int[] end, int rowNum) {
    DateTime from = generator.getInstantForCell(start, rowNum);
    DateTime to = generator.getInstantForCell(end, rowNum).addDuration(generator.getDurationPerCells(1, rowNum));

    //this is to make sure that [0,0] is at least one cell's duration
    return new Period(new DateTime(from.getMillis()), new DateTime(to.getMillis()));
  }

  @Override
  public Period getIntervalForDate(DateTime date) {
    generator.goTo(date);
    return interval();
  }

  @Override
  public int getRowForInstant(DateTime time, int rowsCount) {
    int minutesPerCell = (24 * 60) /rowsCount;

    DateTime mTime = time.toDateTime();
    int minutes = mTime.getMinuteOfDay();
    int row = Math.round(minutes/minutesPerCell);
    return row;
  }


  /**
   * Utility interface that defines a fixed date generator.
   *
   * @author malp
   */
  private interface FixedDateGenerator {
    /**
     * Advances the date.
     */
    void next();

    /**
     * Moves to the specified date.
     *
     * @param start the date
     */
    void goTo(DateTime start);

    /**
     * Goes backward.
     */
    void previous();

    /**
     * Returns the current interval.
     *
     * @return the current interval
     */
    Period interval();

    DateTime getInstantForCell(int[] start, int rowNum);

    Duration getDurationPerCells(int count, int rowNum);
  }

  /**
   * Date generator for days.
   *
   * @author malp
   */
  private class DayDateGenerator implements FixedDateGenerator {

    public Period interval() {
      DateTime start = new DateTime(current.getMillis());
      DateTime end = current.plusDays(1);
      return new Period(start, end);
    }

    @Override
    public DateTime getInstantForCell(int[] start, int rowNum) {
      int distance = start[0];
      Period interval = interval();

      DateTime time = new DateTime(interval.getStartMillis());
      time = time.addDuration(getDurationPerCells(distance, rowNum));
      return time.toDateTime();
    }

    @Override
    public Duration getDurationPerCells(int count, int rowNum) {
      int minutesPerCell = (24 * 60) / rowNum;
      return new Duration(minutesPerCell * count * 60 * 1000);
    }

    public void goTo(DateTime where) {
      current = where.toDateTime();
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
   *
   * @author malp
   */
  private class WeekDateGenerator implements FixedDateGenerator {
    /**
     * defines the number of days in a week
     */
    final int weekSize;

    /**
     * Default constructor.
     */
    private WeekDateGenerator() {
      weekSize = WEEK_SIZE;
    }

    public void goTo(DateTime where) {
      current = where;
    }

    public Period interval() {
      DateTime end = null;
      //adjust to day of week start
      while (current.getDayOfWeek() != START_DAY_OF_WEEK) {
        current = current.plusDays(-1);
      }
      end = current.plusDays(weekSize);
      return new Period(current, end);
    }

    @Override
    public DateTime getInstantForCell(int[] start, int rowNum) {
      int distance = (start[1] * rowNum) + start[0];
      Period curr = interval();
      int minutesPerCell = (24 * 60) / rowNum;
      DateTime time = curr.getStart().toDateTime();
      return time.plusMinutes(minutesPerCell * distance);
//      time.addMinutes(minutesPerCell * distance);
//      return time.toInstant();
    }

    @Override
    public Duration getDurationPerCells(int count, int rowNum) {
      int minutesPerCell = (24 * 60) / rowNum;
      return new Duration(minutesPerCell * count* 60 * 1000);
    }

    public void next() {
      current = current.plusDays(weekSize).toDateTime();
    }

    public void previous() {
      current = current.plusDays(-weekSize).toDateTime();
    }

  }

  /**
   * Date generator for months.
   *
   * @author malp
   */
  private class MonthDateGenerator implements FixedDateGenerator {

    public void goTo(DateTime where) {
      current = where.dayOfMonth(1);

//      MutableDateTime mtd = new MutableDateTime(where.getMillis(), where.getChronology());
//      mtd.setDayOfMonth(1);
//      current = mtd.toDateTime();
    }

    public Period interval() {
      DateTime end = null;
      //      MutableDateTime monthStart = current.toMutableDateTime();
      DateTime iterator = new DateTime(current.getMillis());
      //      monthStart.setDayOfMonth(1);
      end = current.toDateTime().plusMonths(1);

      //adjust start date so that the first week contains the start day 
      while (iterator.isAfter(current)) {//current always points to first day of month
        iterator = iterator.plusDays(-1);
      }
      while (iterator.getDayOfWeek() != START_DAY_OF_WEEK) {
        iterator = iterator.plusDays(-1);
      }
      //adjust end so that last day contains last day of month
      while (end.getDayOfWeek() != START_DAY_OF_WEEK) {
        end = end.plusDays(1);
      }
      end = end.plusDays(-1);//it ends right before the next start of week
      return new Period(iterator, end);
    }

    @Override
    public DateTime getInstantForCell(int[] start, int rowNum) {
      int distance = (start[0] * rowNum) + start[1];
      Period interval = interval();
//      MutableDateTime time = curr.getStart().toMutableDateTime();
      //      return time.toInstant();
      DateTime time = interval.getStart();
      time = time.plusDays(distance);

      return time;
    }

    @Override
    public Duration getDurationPerCells(int count, int rowNum) {
      return new Duration(count, PeriodType.DAYS);
    }

    public void next() {
      moveStart(1);
    }

    public void previous() {
      moveStart(-1);
    }

    /**
     * Utility method that advances the start date pointer a given amount.
     *
     * @param months the number of months to move. Can be negative
     */
    private void moveStart(int months) {
      current = current.plusMonths(months);
    }
  }

}
