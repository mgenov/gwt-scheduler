package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.CalendarPresenter;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.interfaces.navigation.EventNavigationListener;
import gwtscheduler.client.interfaces.uievents.lasso.AbstractLassoEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoCancelSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEndSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEventHandler;
import gwtscheduler.client.interfaces.uievents.lasso.LassoStartSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoUpdateSelectionEvent;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.goda.time.Duration;
import org.goda.time.Instant;
import org.goda.time.Interval;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Generic class for a calendar presenter.
 * @author malp
 */
public abstract class AbstractCalendarPresenter<T extends GenericCalendarDisplay> extends WidgetPresenter<T> implements CalendarPresenter,
    EventNavigationListener, LassoSubject, LassoEventHandler {

  @Inject
  private DateGenerator factory;

  /**
   * Default constructor.
   * @param display the display
   * @param eventBus the event bus
   */
  protected AbstractCalendarPresenter(T display, EventBus eventBus) {
    super(display, eventBus);
    eventBus.addHandler(AbstractLassoEvent.getType(), this);
  }

  @Override
  public Interval getCurrentInterval() {
    return getFactory().interval();
  }

  /**
   * Presenter methods.
   */

  @Override
  public Place getPlace() {
    return null;
  }

  @Override
  public EventNavigationListener getNavigationListener() {
    return this;
  }

  @Override
  public Widget getWidgetDisplay() {
    return getDisplay().asWidget();
  }

  @Override
  public void forceLayout() {
    getDisplay().forceLayout();
  }

  /**
   * View Controller methods
   */

  @Override
  protected void onBind() {
  }

  @Override
  protected void onPlaceRequest(PlaceRequest request) {
  }

  @Override
  protected void onUnbind() {
  }

  @Override
  public void refreshDisplay() {
  }

  @Override
  public void revealDisplay() {
  }

  /**
   * Gets the date factory.
   * @return the date factory
   */
  protected DateGenerator getFactory() {
    return factory;
  }

  /**
   * Lasso Subject methods.
   */

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  @Override
  public abstract Instant getInstantForCell(int[] start);

  /**
   * Gets the duration per cells.
   * @param count the number of cells
   * @return the duration per cells
   */
  protected abstract Duration getDurationPerCells(int count);

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    Instant from = getInstantForCell(start);
    Instant to = getInstantForCell(end).plus(getDurationPerCells(1));
    //this is to make sure that [0,0] is at least one cell's duration
    return new Interval(from, to);
  }

  @Override
  public void onCancelSelection(LassoCancelSelectionEvent event) {
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    if (event.subject == this) {
      Interval time = getIntervalForRange(event.cell, event.endCell);
      GWT.log("-> " + time.toString());
    }
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
  }

}
