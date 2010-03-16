package gwtscheduler.client.widgets.view.common;

import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventIntervalCollisionHelper implements EventCollisionHelper{
  @Override
  public ArrayList<CalendarEvent> checkEventsIntervals(ArrayList<CalendarEvent> events, Event event) {
    ArrayList<CalendarEvent> collisionEvents = new ArrayList<CalendarEvent>();

    for (CalendarEvent e : events) {
      String columnId = (String) e.getEvent().getColumnId();
      if(checkCollision(e.getInterval(),event.getInterval()) && columnId.equals((String) event.getColumnId())){
        collisionEvents.add(e);
      }

    }
    return collisionEvents;
  }

  private boolean checkCollision(Interval intervalA, Interval intervalB) {
    long a1 = intervalA.getStartMillis();
    long a2 = intervalA.getEndMillis();
    long b1 = intervalB.getStartMillis();
    long b2 = intervalB.getEndMillis();

    if(a1<=b1 && b1<a2 ) return true;

    if(b1<a1 && a1<b2 ) return true;

    return false;
  }
}
