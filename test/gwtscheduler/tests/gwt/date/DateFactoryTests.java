package gwtscheduler.tests.gwt.date;

import com.google.gwt.junit.client.GWTTestCase;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.common.calendar.IntervalType;
import gwtscheduler.common.util.DateTime;
import org.junit.Before;
import org.junit.Test;

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
//    config = AppInjector.GIN.getInjector().getConfiguration();

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
    DateTime mdt = new DateTime();
//    mdt.setDayOfMonth(day);
//    mdt.setMonthOfYear(month);
//    mdt.setYear(year);

    gen.goToDate(mdt.toDateTime());
    return gen;
  }

  @Test
  public void testMonthIntervalsJanuary() {
//    Interval intv = moveGenerator(monthf, 1, JANUARY, 2010).interval();
//
//    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
//    assertInstantDate(intv.getStart(), 2009, DECEMBER, 28);
//    assertInstantDate(intv.getEnd(), 2010, JANUARY, 31);
  }

  @Test
  public void testMonthIntervalsJuly() {
//    Interval intv = moveGenerator(monthf, 10, JULY, 2009).interval();
//
//    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
//    assertInstantDate(intv.getStart(), 2009, JUNE, 29);
//    assertInstantDate(intv.getEnd(), 2009, AUGUST, 2);
  }

  @Test
  public void testMonthIntervalsFeb() {
//    Interval intv = moveGenerator(monthf, 10, FEBRUARY, 2009).interval();
//    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
//    assertInstantDate(intv.getStart(), 2009, JANUARY, 26);
//    assertInstantDate(intv.getEnd(), 2009, MARCH, 1);
  }

  @Test
  public void testMonthIntervalsNov() {
//    Interval intv = moveGenerator(monthf, 10, NOVEMBER, 2009).interval();
//    monthf.next();
//    monthf.previous();
//    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
//
//    assertInstantDate(intv.getStart(), 2009, OCTOBER, 26);
//    assertInstantDate(intv.getEnd(), 2009, DECEMBER, 6);
  }

  @Test
  public void testDayIntervalsJuly() {
//    Interval intv = moveGenerator(dayf, 13, JULY, 2009).interval();
//    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
  }

}
