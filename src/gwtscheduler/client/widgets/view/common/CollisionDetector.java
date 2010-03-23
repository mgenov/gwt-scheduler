package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.columns.CalendarColumn;
import gwtscheduler.common.event.CalendarEvent;
import gwtscheduler.common.event.Event;
import org.goda.time.Interval;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CollisionDetector {
  boolean isInCollision(ArrayList<CalendarEvent> events, int columnIndex, Interval interval, Object object);

  boolean isInCollision(Interval interval, Interval currentInterval);
}