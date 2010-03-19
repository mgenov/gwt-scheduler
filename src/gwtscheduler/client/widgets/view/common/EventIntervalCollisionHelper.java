package gwtscheduler.client.widgets.view.common;

import gwtscheduler.common.event.CalendarEvent;
import org.goda.time.Interval;

import java.util.ArrayList;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class EventIntervalCollisionHelper implements EventCollisionHelper {
  @Override
  public boolean isInCollision(ArrayList<CalendarEvent> events, int columnIndex, Interval interval, Object object) {
    for (CalendarEvent event : events) {
      if ((event != object)) {
        if (event.getStartCellPosition()[1] == columnIndex) {
          if (checkCollision(event.getInterval(), interval)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkCollision(Interval intervalA, Interval intervalB) {
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
