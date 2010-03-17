package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import gwtscheduler.common.event.EventPosition;
import org.goda.time.DateTime;
import org.goda.time.Interval;
import org.goda.time.MutableDateTime;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventIntervalCollisionHelperTest {
  private ArrayList<CalendarEvent> events;
  private Event event;

  private CalendarColumn column = new TeamColumn("test column");

  private EventCollisionHelper helper;

   protected DateTime getCurrentDate(int hours) {
    MutableDateTime start = new MutableDateTime(System.currentTimeMillis());
    start.setHourOfDay(hours);
    start.setMinuteOfHour(0);
    start.setMinuteOfHour(0);
    start.setMillisOfSecond(0);
    DateTime date = start.toDateTime();
    return date;
  }



  @Test
  public void  testCheck(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 6;
    int b2  = 7;
    assertFalse(checkColission(a1,a2,b1,b2));
  }
  @Test
  public void  testCheck1(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 1;
    int b2  = 3;
    assertFalse(checkColission(a1,a2,b1,b2));
  }
   @Test
  public void  testCheck2(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 5;
    int b2  = 8;
   assertFalse(checkColission(a1,a2,b1,b2));
  }
  @Test
  public void  testCheck3(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 4;
    int b2  = 8;
   assertTrue(checkColission(a1,a2,b1,b2));
  }
  @Test
  public void  testCheck4(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 1;
    int b2  = 4;
   assertTrue(checkColission(a1,a2,b1,b2));
  }
   @Test
  public void  testCheck5(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 3;
    int b2  = 4;
   assertTrue(checkColission(a1,a2,b1,b2));
  }
   @Test
  public void  testCheck6(){
    int a1  = 3;
    int a2  = 5;
    int b1  = 3;
    int b2  = 8;
   assertTrue(checkColission(a1,a2,b1,b2));
  }
   @Test
  public void  testCheck7(){
    int a1  = 3;
    int a2  = 6;
    int b1  = 4;
    int b2  = 5;
   assertTrue(checkColission(a1,a2,b1,b2));
  }
   @Test
  public void  testCheck8(){
    int a1  = 3;
    int a2  = 6;
    int b1  = 3;
    int b2  = 6;
   assertTrue(checkColission(a1,a2,b1,b2));
  }



  private boolean checkColission(int a1,int a2,int b1,int b2){
    if(a1<=b1 && b1<a2 ) return true;

    if(b1<a1 && a1<b2 ) return true;
    
    return false;
  }


  private void asserts(ArrayList<CalendarEvent> expected, ArrayList<CalendarEvent> actual) {
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i),actual.get(i));
    }
  }


  class TeamTaskEvent implements Event{
  private TestTask testTask;
  private CalendarColumn column;

  public TeamTaskEvent(TestTask testTask, CalendarColumn column) {
    this.testTask = testTask;
    this.column = column;
  }


  @Override
  public Interval getInterval() {
    return testTask.getInterval();
  }

  @Override
  public void setInterval(Interval interval) {
    testTask.setInterval(interval);
  }

  @Override
  public String getTitle() {
    return testTask.getTitle();
  }

  @Override
  public Object getColumnId() {
    return column.getId();
  }
  }

  public class TestTask {

  private String description;
  private Interval interval;
  private String title;
  private Integer duration;

  public TestTask() {
  }
     public TestTask(String title,Interval interval) {
       this.title = title;
       this.interval = interval;
     }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Interval getInterval() {
    return interval;
  }

  public void setInterval(Interval interval) {
    this.interval = interval;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public Integer getDuration() {
    return duration;
  }
}

   public static class TeamColumn implements CalendarColumn {
    private final Team team;

    public TeamColumn(String name) {
      team = new Team(name);
    }

    @Override
    public String getTitle() {
      return team.getName();
    }

    @Override
    public Object getObject() {
      return team;
    }

    @Override
    public void setObject(Object object) {
    }

    @Override
    public boolean isEventForColumn(Event event) {
      if (((String) event.getColumnId()).equals((String) getId())) {
        return true;
      } else {
        return false;
      }
    }

    public Object getId() {
      return getTitle();
    }
  }

  public static class Team {
    private String name;

    public Team(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
