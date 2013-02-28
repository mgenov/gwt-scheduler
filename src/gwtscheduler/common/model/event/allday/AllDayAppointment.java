package gwtscheduler.common.model.event.allday;

import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.EventType;
import gwtscheduler.common.util.Period;

/**
 * Defines an all-day event.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AllDayAppointment extends AbstractAppointment {

  /**
   * @param interval
   * @param type
   */
  public AllDayAppointment(Period interval) {
    super(interval, EventType.ALLDAY);
  }

  @Override
  protected Period filter(Period interval) {
    return interval;
  }

}
