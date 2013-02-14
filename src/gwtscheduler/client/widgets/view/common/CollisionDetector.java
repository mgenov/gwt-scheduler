package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.common.util.Period;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public interface CollisionDetector {
  boolean isInCollision(ArrayList<CalendarEvent> events, int columnIndex, Period interval, Object object);

  boolean isInCollision(Period interval, Period currentInterval);
}
