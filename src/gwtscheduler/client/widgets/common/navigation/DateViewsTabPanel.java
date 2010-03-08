package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.core.client.GWT;
import gwtscheduler.client.dragndrop.DragOutHandler;
import gwtscheduler.client.dragndrop.DragInEvent;
import gwtscheduler.client.dragndrop.DragInHandler;
import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.dragndrop.DropZone;
import gwtscheduler.client.modules.annotation.Day;
import gwtscheduler.client.modules.annotation.Month;
import gwtscheduler.client.modules.annotation.Week;
import gwtscheduler.client.modules.views.MainView;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.widgets.common.CalendarPresenter;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Main navigation panel.
 * @author malp
 */
//TODO migrate to MVP
public class DateViewsTabPanel extends Composite implements MainView, BeforeSelectionHandler<Integer>, DropZone {

  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** widget delegate */
  private DecoratedTabPanel impl;
  /** presenters array */
  private CalendarPresenter[] presenters;

  /**
   * Default constructor.
   * @param day
   * @param week
   * @param month
   */
  @Inject
  public DateViewsTabPanel(final @Day CalendarPresenter day, final @Week CalendarPresenter week, @Month CalendarPresenter month) {
    impl = new DecoratedTabPanel();
    initWidget(impl);
    impl.addBeforeSelectionHandler(this);

    presenters = new CalendarPresenter[3];
    presenters[0] = day;
    presenters[1] = week;
    presenters[2] = month;
    add(day);
    add(week);
    add(month);


    // TODO: delete this 2 handlers.. this is added only for researching
    addDropHandler(new DropHandler(){
      @Override
      public void onDrop(DropEvent event) {
        day.onDropEvent(event);
        week.onDropEvent(event);
        GWT.log("Dropped in x: " + event.getEndX() + " y: " + event.getMouseY(), null);
      }
    });
//
//    addDragOverHandler(new DragInHandler(){
//      @Override
//      public void onDragOverlap(DragInEvent event) {
//        GWT.log("Dragged in drag zone", null);
//        event.getFrame().setPixelSize(20, 20);
//      }
//    });
  }

  @Override
  public Widget asWidget() {
    return this;
  }

  @Override
  public void forceLayout() {
    for (CalendarPresenter p : presenters) {
      p.forceLayout();
    }
  }

  public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
    CalendarPresenter presenter = presenters[event.getItem()];
    presenter.forceLayout();
  }

  /**
   * Adds a new view to this tab panel.
   * @param presenter the controller
   */
  private void add(CalendarPresenter presenter) {
    Widget view = presenter.getWidgetDisplay();
    TabPanelContainer container = new TabPanelContainer();
    container.add(view);

    impl.add(container, presenter.getTabLabel());
  }

  /**
   * Selects a tab.
   * @param i the tab index
   */
  public void selectTab(int i) {
    impl.selectTab(i);
  }

  @Override
  public void addDropHandler(DropHandler handler) {
    addHandler(handler, DropEvent.TYPE);
  }

  @Override
  public void addDragOverHandler(DragInHandler handler) {
    addHandler(handler, DragInEvent.TYPE);
  }

  @Override
  public void addDragOutHandler(DragOutHandler handler) {
  }
}
