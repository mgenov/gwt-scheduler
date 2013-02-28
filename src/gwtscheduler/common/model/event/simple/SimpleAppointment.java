package gwtscheduler.common.model.event.simple;

import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.EventType;
import gwtscheduler.common.util.Period;

/**
 * Defines a simple event, ie, an event that does not last more than a day.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class SimpleAppointment extends AbstractAppointment {

  /**
   * @param start
   * @param end
   */
  public SimpleAppointment(Period interval) {
    super(interval, EventType.SIMPLE);
  }

  @Override
  protected Period filter(Period interval) {
    return interval;
  }

}
