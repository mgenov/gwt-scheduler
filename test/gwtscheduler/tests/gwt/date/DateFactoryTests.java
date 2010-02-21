package gwtscheduler.tests.gwt.date;

import static gwtscheduler.tests.gwt.TestUtils.assertInstantDate;
import static org.goda.time.DateTimeConstants.AUGUST;
import static org.goda.time.DateTimeConstants.DECEMBER;
import static org.goda.time.DateTimeConstants.FEBRUARY;
import static org.goda.time.DateTimeConstants.JANUARY;
import static org.goda.time.DateTimeConstants.JULY;
import static org.goda.time.DateTimeConstants.JUNE;
import static org.goda.time.DateTimeConstants.MARCH;
import static org.goda.time.DateTimeConstants.NOVEMBER;
import static org.goda.time.DateTimeConstants.OCTOBER;
import gwtscheduler.client.modules.AppInjector;
import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
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
  public void testMonthIntervalsJanuary() {
    Interval intv = moveGenerator(monthf, 1, JANUARY, 2010).interval();

    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, DECEMBER, 28);
    assertInstantDate(intv.getEnd(), 2010, JANUARY, 31);
  }

  @Test
  public void testMonthIntervalsJuly() {
    Interval intv = moveGenerator(monthf, 10, JULY, 2009).interval();

    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, JUNE, 29);
    assertInstantDate(intv.getEnd(), 2009, AUGUST, 2);
  }

  @Test
  public void testMonthIntervalsFeb() {
    Interval intv = moveGenerator(monthf, 10, FEBRUARY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, JANUARY, 26);
    assertInstantDate(intv.getEnd(), 2009, MARCH, 1);
  }

  @Test
  public void testMonthIntervalsNov() {
    Interval intv = moveGenerator(monthf, 10, NOVEMBER, 2009).interval();
    monthf.next();
    monthf.previous();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());

    assertInstantDate(intv.getStart(), 2009, OCTOBER, 26);
    assertInstantDate(intv.getEnd(), 2009, DECEMBER, 6);
  }

  @Test
  public void testDayIntervalsJuly() {
    Interval intv = moveGenerator(dayf, 13, JULY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
  }

}
