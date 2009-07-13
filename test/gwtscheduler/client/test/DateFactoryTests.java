package gwtscheduler.client.test;

import static org.goda.time.DateTimeConstants.AUGUST;
import static org.goda.time.DateTimeConstants.FEBRUARY;
import static org.goda.time.DateTimeConstants.JANUARY;
import static org.goda.time.DateTimeConstants.JULY;
import static org.goda.time.DateTimeConstants.JUNE;
import static org.goda.time.DateTimeConstants.MARCH;
import gwtscheduler.client.interfaces.DateGenerator;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.common.calendar.IntervalType;

import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * Test case for date factory.
 * @author malp
 */
public class DateFactoryTests extends GWTTestCase {

  DateGenerator dayf;
  DateGenerator weekf;
  DateGenerator monthf;

  DateTime now;
  AppConfiguration config;

  @Override
  public String getModuleName() {
    return "gwtscheduler.Tests";
  }

  @Before
  @Override
  public void gwtSetUp() {
    config = AppInjector.GIN.getInjector().getConfiguration();

    now = new DateTime();

    dayf = AppInjector.GIN.getInjector().getDateGenerator();
    weekf = AppInjector.GIN.getInjector().getDateGenerator();
    monthf = AppInjector.GIN.getInjector().getDateGenerator();

    dayf.init(IntervalType.DAY, now);
    weekf.init(IntervalType.WEEK, now);
    monthf.init(IntervalType.MONTH, now);
  }

  /**
   * Utility method to move a generator to a given date
   * @param gen the generator
   * @param day the day
   * @param month the month
   * @param year the year
   * @return the generator
   */
  protected DateGenerator moveGenerator(DateGenerator gen, int day, int month, int year) {
    MutableDateTime mdt = now.toMutableDateTime();
    mdt.setDayOfMonth(day);
    mdt.setMonthOfYear(month);
    mdt.setYear(year);

    gen.goTo(mdt.toDateTime());
    return gen;
  }

  @Test
  public void testMonthIntervalsJuly() {
    Interval intv = moveGenerator(monthf, 10, JULY, 2009).interval();

    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());

    assertEquals(29, intv.getStart().getDayOfMonth());
    assertEquals(JUNE, intv.getStart().getMonthOfYear());

    assertEquals(2, intv.getEnd().getDayOfMonth());
    assertEquals(AUGUST, intv.getEnd().getMonthOfYear());
  }

  @Test
  public void testMonthIntervalsFeb() {
    Interval intv = moveGenerator(monthf, 10, FEBRUARY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());

    assertEquals(26, intv.getStart().getDayOfMonth());
    assertEquals(JANUARY, intv.getStart().getMonthOfYear());

    assertEquals(1, intv.getEnd().getDayOfMonth());
    assertEquals(MARCH, intv.getEnd().getMonthOfYear());
  }
  
  @Test
  public void testDayIntervalsJuly(){
    Interval intv = moveGenerator(dayf, 13, JULY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
  }

}
