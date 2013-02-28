package gwtscheduler.common.util;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertThat;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class DateTimeTest {

  private Date date = new DateHelper(2013, 2, 20, 12, 15, 0).getDate();
  private DateTime dateTime = new DateTime(date);


  @Test
  public void trimToStart() throws Exception {
    Date expectedDate = new DateHelper(2013, 2, 20, 0, 0, 0).getDate();
    System.out.println(expectedDate);
    System.out.println(dateTime.trimToStart());
    assertThat(dateTime.trimToStart().asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void trimToEnd() throws Exception {
    Date expectedDate = new DateHelper(2013, 2, 20, 23, 59, 59).getDate();
    System.out.println(expectedDate);
    System.out.println(dateTime.trimToEnd());
    assertThat(dateTime.trimToEnd().asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void getYear() throws Exception {
    assertThat(dateTime.getYear(), CoreMatchers.is(CoreMatchers.equalTo(2013)));
  }

  @Test
  public void getMonth() throws Exception {
    assertThat(dateTime.getMonthOfYear(), CoreMatchers.is(CoreMatchers.equalTo(2)));
  }

  @Test
  public void getDay() throws Exception {
    System.out.println(dateTime);
    assertThat(dateTime.getDayOfMonth(), CoreMatchers.is(CoreMatchers.equalTo(20)));
  }

  @Test
  public void getDayOfWeek() throws Exception {
    assertThat(dateTime.getDayOfWeek(), CoreMatchers.is(CoreMatchers.equalTo(3)));
  }


  @Test
  public void getMinuteOfDay() throws Exception {
    assertThat(dateTime.getMinuteOfDay(), CoreMatchers.is(CoreMatchers.equalTo(12*60+15)));
  }

  @Test
  public void getMinutes() throws Exception {
    assertThat(dateTime.getMinutes(), CoreMatchers.is(CoreMatchers.equalTo(15)));
  }

  @Test
  public void getHourOfDay() throws Exception {
    assertThat(dateTime.hourOfDay(), CoreMatchers.is(CoreMatchers.equalTo(12)));
  }

  @Test
  public void plusHours() throws Exception {
    Date expectedDate = new DateHelper(2013, 2, 20, 14, 15, 0).getDate();
    assertThat(dateTime.plusHours(2).asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void plusMinutes() throws Exception {
    Date expectedDate = new DateHelper(2013, 2, 20, 12, 37, 0).getDate();
    assertThat(dateTime.plusMinutes(22).asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void plusMonths() throws Exception {
    Date expectedDate = new DateHelper(2013, 5, 20, 12, 15, 0).getDate();
    assertThat(dateTime.plusMonths(3).asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void setMills() throws Exception {
    Date expectedDate = new DateHelper(2013, 5, 20, 12, 15, 0).getDate();
    dateTime.setMillis(expectedDate.getTime());
    assertThat(dateTime.asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }

  @Test
  public void setMillisOfMinute() throws Exception {
    long expectedTime = dateTime.getMillis();
    assertThat(dateTime.setMillisOfMinute(100).getMillis(), CoreMatchers.is(CoreMatchers.equalTo(expectedTime + 100)));
    assertThat(dateTime.setMillisOfMinute(999).getMillis(), CoreMatchers.is(CoreMatchers.equalTo(expectedTime + 999)));
  }

  @Test
  public void plusDays() throws Exception {
    Date expectedDate = new DateHelper(2013, 2, 23, 12, 15, 0).getDate();
    assertThat(dateTime.plusDays(3).asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));

    expectedDate = new DateHelper(2013, 3, 5, 12, 15, 0).getDate();
    assertThat(dateTime.plusDays(13).asDate(), CoreMatchers.is(CoreMatchers.equalTo(expectedDate)));
  }
}
