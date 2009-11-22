package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.interfaces.LassoSubject;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.place.Place;
import net.customware.gwt.presenter.client.place.PlaceRequest;
import net.customware.gwt.presenter.client.widget.WidgetPresenter;

import org.goda.time.Instant;
import org.goda.time.Interval;

import com.google.gwt.user.client.Element;

/**
 * Generic class for a calendar presenter.
 * @author malp
 */
public class GenericCalendarPresenter extends
    WidgetPresenter<GenericCalendarDisplay> implements LassoSubject {

  /**
   * Default constructor.
   * @param display
   * @param eventBus
   */
  public GenericCalendarPresenter(GenericCalendarDisplay display,
      EventBus eventBus) {
    super(display, eventBus);
  }

  /**
   * Presenter methods.
   */

  @Override
  public Place getPlace() {
    return null;
  }

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

  @Override
  public int getColNum() {
    return 0;
  }

  @Override
  public int getRowNum() {
    return 0;
  }

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
    return null;
  }

  @Override
  public Interval getIntervalForRange(int[] start, int[] end) {
    return null;
  }

  @Override
  public List<Cell<Element>> getLassoSubjects() {
    return getDisplay().getVisibleElements();
  }

}
