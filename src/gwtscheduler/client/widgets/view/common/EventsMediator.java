package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.event.AbstractLassoEvent;
import gwtscheduler.client.widgets.common.event.LassoCancelSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEndSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoEventHandler;
import gwtscheduler.client.widgets.common.event.LassoStartSelectionEvent;
import gwtscheduler.client.widgets.common.event.LassoUpdateSelectionEvent;
import net.customware.gwt.presenter.client.EventBus;

import org.goda.time.Interval;

import com.google.gwt.user.client.Window;

/**
 * @author malp
 */
public class EventsMediator implements LassoEventHandler {

  private CalendarPresenter presenter;

  /**
   * Creates a new events mediator.
   * @param pr the presenter
   * @param eventBus the event bus
   */
  public EventsMediator(CalendarPresenter pr, EventBus eventBus) {
    this.presenter = pr;
    eventBus.addHandler(AbstractLassoEvent.getType(), this);
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    if (event.subject == presenter) {
      Interval time = presenter.getIntervalForRange(event.cell, event.endCell);
      Window.alert(time.getStart() + "->" + time.getEnd());
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
