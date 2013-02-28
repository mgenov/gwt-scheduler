package gwtscheduler.tests.gwt.date;

import gwtscheduler.client.modules.config.AppConfiguration;
import gwtscheduler.client.modules.config.DefaultAppConfiguration;
import gwtscheduler.client.utils.GenericDateGenerator;
import gwtscheduler.client.widgets.common.navigation.DateGenerator;
import gwtscheduler.common.calendar.IntervalType;
import gwtscheduler.common.util.DateHelper;
import gwtscheduler.common.util.DateTime;
import gwtscheduler.common.util.Period;
import junit.framework.TestResult;
import org.junit.Before;
import org.junit.Test;

import static gwtscheduler.common.util.DateTimeConstants.*;
import static gwtscheduler.tests.gwt.TestUtils.assertInstantDate;
import static junit.framework.Assert.assertEquals;

/**
 * Test case for date factory.
 * @author malp
 */
public class DateFactoryTests implements junit.framework.Test {

  DateGenerator dayf;
  DateGenerator weekf;
  DateGenerator monthf;

  DateTime now;
  AppConfiguration config;

//  @Override
  public String getModuleName() {
    return "gwtscheduler.Tests";
  }

  @Before
//  @Override
  public void gwtSetUp() {
    config = new DefaultAppConfiguration();
//    config = AppInjector.GIN.getInjector().getConfiguration();

    now = new DateTime();

//    dayf = AppInjector.GIN.getInjector().getDateGenerator();
    dayf = new GenericDateGenerator();
//    weekf = AppInjector.GIN.getInjector().getDateGenerator();
    weekf = new GenericDateGenerator();
//    monthf = AppInjector.GIN.getInjector().getDateGenerator();
    monthf = new GenericDateGenerator();

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
    DateTime mdt = new DateTime(new DateHelper(year,month,day,0,0,0).getDate());
    gen.goToDate(mdt);
    return gen;
  }

  @Test
  public void testMonthIntervalsJanuary() {
    Period intv = moveGenerator(monthf, 1, JANUARY, 2010).interval();

    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, DECEMBER, 28);
    assertInstantDate(intv.getEnd(), 2010, JANUARY, 31);
  }

  @Test
  public void testMonthIntervalsJuly() {
    Period intv = moveGenerator(monthf, 10, JULY, 2009).interval();

    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, JUNE, 29);
    assertInstantDate(intv.getEnd(), 2009, AUGUST, 2);
  }

  @Test
  public void testMonthIntervalsFeb() {
    Period intv = moveGenerator(monthf, 10, FEBRUARY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
    assertInstantDate(intv.getStart(), 2009, JANUARY, 26);
    assertInstantDate(intv.getEnd(), 2009, MARCH, 1);
  }

  @Test
  public void testMonthIntervalsNov() {
    Period intv = moveGenerator(monthf, 10, NOVEMBER, 2009).interval();
    monthf.next();
    monthf.previous();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());

    assertInstantDate(intv.getStart(), 2009, OCTOBER, 26);
    assertInstantDate(intv.getEnd(), 2009, DECEMBER, 6);
  }

  @Test
  public void testDayIntervalsJuly() {
    Period intv = moveGenerator(dayf, 13, JULY, 2009).interval();
    assertEquals(config.startDayOfWeek(), intv.getStart().getDayOfWeek());
  }

  @Override
  public int countTestCases() {
    return 0;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void run(TestResult testResult) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
