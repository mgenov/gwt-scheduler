package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import gwtscheduler.client.widgets.view.columns.CalendarColumn;
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
      if(columnId.equals((String) event.getColumnId())){
         if(checkCollision(e.getInterval(),event.getInterval()) )  collisionEvents.add(e);
      }
    }
    return collisionEvents;
  }

  @Override
  public boolean checkEventsIntervals(ArrayList<CalendarEvent> events, Interval interval, CalendarColumn column) {
    for (CalendarEvent event : events) {
      if(column.isEventForColumn(event.getEvent())){
        if(checkCollision(event.getInterval(),interval)) return true;
      }
    }
    return false;
  }

  private boolean checkCollision(Interval intervalA, Interval intervalB) {
    long a1 = intervalA.getStartMillis();
    long a2 = intervalA.getEndMillis();
    long b1 = intervalB.getStartMillis();
    long b2 = intervalB.getEndMillis();
//    GWT.log(a1+" --A-- "+ a2,null);
//    GWT.log(b1+" --B-- "+b2,null);

    if(a1<=b1 && b1<a2 ) return true;

    if(b1<a1 && a1<b2 ) return true;

    return false;
  }
}
