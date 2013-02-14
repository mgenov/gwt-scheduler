package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.common.util.Interval;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CollisionDetector {
  boolean isInCollision(ArrayList<CalendarEvent> events, int columnIndex, Interval interval, Object object);

  boolean isInCollision(Interval interval, Interval currentInterval);
}
