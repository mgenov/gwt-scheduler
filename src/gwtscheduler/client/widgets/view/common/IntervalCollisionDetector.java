package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.view.event.CalendarEvent;
import gwtscheduler.common.util.Period;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class IntervalCollisionDetector implements CollisionDetector {
  @Override
  public boolean isInCollision(ArrayList<CalendarEvent> events, int columnIndex, Period interval, Object object) {
    for (CalendarEvent event : events) {
      if ((event != object)) {
        int[] pos = new int[2];
        pos = event.getStartCellPosition();
        if (pos[1] == columnIndex) {
          if (checkCollision(event.getInterval(), interval)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean isInCollision(Period interval, Period currentInterval) {
    return checkCollision(interval,currentInterval);  
  }

  private boolean checkCollision(Period intervalA, Period intervalB) {
    long a1 = intervalA.getStartMillis();
    long a2 = intervalA.getEndMillis();
    long b1 = intervalB.getStartMillis();
    long b2 = intervalB.getEndMillis();

    if (a1 <= b1 && b1 < a2) {
      return true;
    } else if (b1 < a1 && a1 < b2) {
      return true;
    }

    return false;
  }
}
