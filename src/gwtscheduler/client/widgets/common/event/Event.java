package gwtscheduler.client.widgets.common.event;

import org.goda.time.Interval;

/**
 * Event represents a single Event item in the calendar.
 * <p/>
 * 
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public interface Event {

  /**
   * Gets the interval of that event.
   * @return the interval value which indicates start/end and date of the current event
   */
  Interval getInterval();

  /**
   * Gets the event title.
   * @return the event title
   */
  String getTitle();
}
