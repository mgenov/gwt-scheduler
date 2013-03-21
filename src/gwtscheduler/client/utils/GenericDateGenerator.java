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
  private static final int START_DAY_OF_WEEK = 1;
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
  private int startHour;
  private int endHour;
  private int hours;


  /**
   * Default constructor.
   */
  public GenericDateGenerator() {
  }

  public DateTime current() {
    return current;
  }

  public void init(IntervalType interval, DateTime start, int startHour, int endHour) {
    this.startHour = startHour;
    this.endHour = endHour;
    //TODO maybe use a flag|bitmask for resetting fields?
    this.current = start.trimToStart().plusHours(startHour);


    if (IntervalType.DAY.equals(interval)) {
      generator = new DayDateGenerator();
    } else if (IntervalType.WEEK.equals(interval)) {
      generator = new WeekDateGenerator();
    } else if (IntervalType.MONTH.equals(interval)) {
      generator = new MonthDateGenerator();
    } else {
      throw new IllegalArgumentException("Unknown interval type: " + interval.toString());
    }
    goToDate(current);

    currentInterval = generator.visiblePeriod();
    hours =  endHour-startHour;
  }

  public void goToDate(DateTime start) {
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

  public Period interval() {
    return generator.visiblePeriod();
  }

  @Override
  public Period currentInterval() {
    generator.goTo(currentInterval.getStart());
    return currentInterval;
  }

  @Override
  public DateTime getStartTimeForCell(int[] cell, int rowNum) {
    return generator.getStartTimeForCell(cell, rowNum);
  }

  @Override
  public Period getIntervalForRange(int[] start, int[] end, int rowNum) {
    DateTime from = generator.getStartTimeForCell(start, rowNum);
    DateTime to = generator.getStartTimeForCell(end, rowNum).addDuration(generator.getDurationPerCells(1, rowNum, hours));

    //this is to make sure that [0,0] is at least one cell's duration
    return new Period(new DateTime(from.getMillis()), new DateTime(to.getMillis()));
  }

  @Override
  public Period getIntervalForDate(DateTime date) {
    //todo bug here
    generator.goTo(date.trimToStart().plusHours(startHour));
    return interval();
  }

  @Override
  public int getRowForInstant(DateTime time, int rowsCount) {
    int minutesPerCell = (hours * 60) /rowsCount;
    int minutes = time.getMinuteOfDay() - (startHour * 60) ;
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
     * @param endHour
     */
    Period visiblePeriod();

    DateTime getStartTimeForCell(int[] cell, int totalRowsCount);

    Duration getDurationPerCells(int row, int totalRowsCount, int hours);
  }

  /**
   * Date generator for days.
   *
   * @author malp
   */
  private class DayDateGenerator implements FixedDateGenerator {

    public Period visiblePeriod() {
      DateTime start = new DateTime(current.getMillis());
      DateTime end = current.plusHours(hours);
      return new Period(start, end);
    }

    @Override
    public DateTime getStartTimeForCell(int[] cell, int totalRowsCount) {
      int cellRow = cell[0];

      DateTime start = currentInterval.getStart();

      Duration durationPerCell = getDurationPerCells(cellRow, totalRowsCount, hours);

      start = start.addDuration(durationPerCell);

      return start;
    }

    @Override
    public Duration getDurationPerCells(int row, int totalRowsCount, int hours) {
      int minutesPerCell = (hours * 60) / totalRowsCount;
      return new Duration(minutesPerCell * row * 60 * 1000);
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

    public Period visiblePeriod() {
      DateTime end = null;
      //adjust to day of week start
      while (current.getDayOfWeek() != START_DAY_OF_WEEK) {
        current = current.plusDays(-1);
      }
      end = current.plusDays(weekSize);
      return new Period(current, end);
    }

    @Override
    public DateTime getStartTimeForCell(int[] cell, int totalRowsCount) {
      int distance = (cell[1] * totalRowsCount) + cell[0];

      DateTime start = currentInterval.getStart();

      int minutesPerCell = (hours * 60) / totalRowsCount;

      return start.plusMinutes(minutesPerCell * distance);
    }

    @Override
    public Duration getDurationPerCells(int row, int totalRowsCount, int hours) {
      int minutesPerCell = (hours * 60) / totalRowsCount;
      return new Duration(minutesPerCell * row * 60 * 1000);
    }

    public void next() {
      current = current.plusDays(weekSize);
    }

    public void previous() {
      current = current.plusDays(-weekSize);
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
    }

    public Period visiblePeriod() {
      DateTime end = null;
      //      MutableDateTime monthStart = current.toMutableDateTime();
      DateTime iterator = new DateTime(current.getMillis());
      //      monthStart.setDayOfMonth(1);
      end = current.plusMonths(1);

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
    public DateTime getStartTimeForCell(int[] cell, int totalRowsCount) {
      int distance = (cell[0] * totalRowsCount) + cell[1];
      DateTime start = currentInterval.getStart();
      start = start.plusDays(distance);

      return start;
    }

    @Override
    public Duration getDurationPerCells(int row, int totalRowsCount, int hours) {
      return new Duration(row, PeriodType.DAYS);
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
