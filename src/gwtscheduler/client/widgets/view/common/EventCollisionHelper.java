package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface EventCollisionHelper {
  ArrayList<CalendarEvent> checkEventsIntervals(ArrayList<CalendarEvent> events, Event event);

  boolean checkEventsIntervals(ArrayList<CalendarEvent> events, Interval interval, CalendarColumn column, Object dropObject);

  boolean checkEventsIntervals(ArrayList<CalendarEvent> events, Interval interval, CalendarEvent calendarEvent);
}
