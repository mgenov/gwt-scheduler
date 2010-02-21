package gwtscheduler.client.widgets.common.decoration;

import org.goda.time.Interval;

import com.google.gwt.user.client.Element;

/**
 * @author malp
 */
public interface IntervalDecorator {

  /**
   * Decorates an event.
   * @param i the interval
   * @param event the event
   */
  void decorate(Interval i, Element event);
}
