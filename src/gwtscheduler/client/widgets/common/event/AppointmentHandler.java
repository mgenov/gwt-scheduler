package gwtscheduler.client.widgets.common.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Interface for resize-aware widgets.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface AppointmentHandler extends EventHandler {

  /**
   * Fired when a new appointment is to be added.
   * @param evt the appointment
   */
  void onAddEvent(AppointmentEvent evt);
}
