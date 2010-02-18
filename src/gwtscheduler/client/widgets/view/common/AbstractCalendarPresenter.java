package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.CalendarPresenter;
import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.navigation.DateGenerator;
import gwtscheduler.client.interfaces.navigation.EventNavigationListener;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.goda.time.Instant;
import org.goda.time.Interval;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Generic class for a calendar presenter.
 * @author malp
 */
public abstract class AbstractCalendarPresenter<T extends GenericCalendarDisplay> extends WidgetPresenter<T> implements CalendarPresenter,
    EventNavigationListener, LassoSubject {

  @Inject
  private DateGenerator factory;

  /**
   * Default constructor.
   * @param display
   * @param eventBus
   */
  protected AbstractCalendarPresenter(T display, EventBus eventBus) {
    super(display, eventBus);
  }

  /**
   * Gets the date factory.
   * @return the date factory
   */
  protected DateGenerator getFactory() {
    return factory;
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
   * Lasso Subject methods.
   */

  //  @Override
  //  public int getColNum() {
  //    return 0;
  //  }
  //
  //  @Override
  //  public int getRowNum() {
  //    return 0;
  //  }

  @Override
  public int getWidth() {
    return getDisplay().getWidth();
  }

  @Override
  public int getHeight() {
    return getDisplay().getHeight();
  }

  @Override
  public Instant getInstantForCell(int[] start) {
    //XXX implement
    return null;
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    //XXX implement
    return null;
  }

  @Override
  public List<Cell<Element>> getLassoSubjects() {
    return getDisplay().getVisibleElements();
  }

}
