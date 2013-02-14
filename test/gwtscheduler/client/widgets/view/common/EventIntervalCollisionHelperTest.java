package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.client.widgets.view.event.Event;
import gwtscheduler.common.util.DateTime;
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


  private CollisionDetector detector;

  protected DateTime getCurrentDate(int hours) {
    DateTime start = new DateTime(System.currentTimeMillis());
    start.setHours(hours);
    start.setMinutes(0);
    start.setMinutes(0);
    start.setMillis(0);
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
}
