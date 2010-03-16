package gwtscheduler.client.widgets.view.common;

import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface EventCollisionHelper {
  ArrayList<CalendarEvent> checkEventsIntervals(ArrayList<CalendarEvent> events, Event event);
}
