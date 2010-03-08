package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.modules.EventBus;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.event.AbstractLassoEvent;
import gwtscheduler.client.widgets.common.event.AppointmentEvent;
import gwtscheduler.client.widgets.common.event.LassoCancelSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEndSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEventHandler;
import gwtscheduler.client.widgets.common.event.LassoStartSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoUpdateSelectionEvent;
import gwtscheduler.common.model.event.AbstractAppointment;
import gwtscheduler.common.model.event.simple.SimpleAppointment;

import org.goda.time.Interval;

/**
 * Mediates event addition.
 * @author malp
 */
public class EventsMediator implements LassoEventHandler {

  private CalendarPresenter presenter;
  private EventBus eventBus;

  /**
   * Creates a new events mediator.
   * @param pr the presenter
   * @param eventBus the event bus
   */
  public EventsMediator(CalendarPresenter pr, EventBus eventBus) {
    this.presenter = pr;
    this.eventBus = eventBus;
    eventBus.addHandler(AbstractLassoEvent.getType(), this);
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    if (event.subject == presenter) {
      Interval time = presenter.getIntervalForRange(event.cell, event.endCell);
      //      Window.alert(time.getStart() + "->" + time.getEnd());

      AbstractAppointment appointment = new SimpleAppointment(time);
      AppointmentEvent evt = new AppointmentEvent(presenter, appointment, event.cell, event.endCell);
      eventBus.fireEvent(evt);
    }
  }

  @Override
  public void onCancelSelection(LassoCancelSelectionEvent event) {
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
  }
}
