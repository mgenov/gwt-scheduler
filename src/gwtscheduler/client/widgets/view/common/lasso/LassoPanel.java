package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.EventWidgetFactory;
import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.utils.PointUtils;
import gwtscheduler.client.widgets.view.common.EventWidget;
import gwtscheduler.client.widgets.view.common.GenericEventWidgetFactory;

import java.util.List;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * Lasso panel. Responsible for displaying user lasso selections.
 * @author malp
 */
class LassoPanel extends AbsolutePanel implements MouseDownHandler,
    MouseMoveHandler, MouseUpHandler, WidgetResizeHandler {

  /** the lasso subject grid */
  private LassoSubject subject;
  /** indicates if a lasso is being selected or not */
  private boolean isMouseDown = false;
  /** the lasso starting position */
  private int[] startPos;
  /** the lasso strategy */
  private LassoStrategy strategy;

  //TODO bind with GIN
  private EventWidgetFactory eventFactory;

  /**
   * Default constructor.
   * @param strat
   */
  LassoPanel() {
    eventFactory = new GenericEventWidgetFactory();
    // style
    addStyleName(Resources.dayWeekCss().lasso());

    addDomHandler(this, MouseDownEvent.getType());
    addDomHandler(this, MouseUpEvent.getType());
    addDomHandler(this, MouseMoveEvent.getType());

    DOM.setStyleAttribute(getElement(), "opacity", "0.5");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=50)");
  }

  /**
   * Sets the current lasso strategy.
   * @param strat the strategy
   */
  public void setStrategy(LassoStrategy strat) {
    strategy = strat;
  }

  /**
   * Selects a range of lasso cells.
   * @param p1 the first position
   * @param p2 the second position
   */
  private void selectRange(int[] p1, int[] p2) {
    List<int[]> range = strategy.getBlocks(subject, p1, p2);
    assert range.size() >= 2 : "Event blocks are less than 2.";
    assert range.size() % 2 == 0 : "Odd number of events";

    for (int i = 0; i < range.size(); i += 2) {
      int[] from = range.get(i);
      int[] to = range.get(i + 1);
      EventWidget event = eventFactory.createEvent(subject, from, to);

      int[] coords = calculateLeftTop(from);
      add(event, coords[0], coords[1]);
    }

  }

  /**
   * Sets the lasso subject.
   * @param subject the subject
   */
  void setLassoSubject(LassoSubject subject) {
    assert subject != null : "Lasso subject cannot be null.";
    this.subject = subject;
  }

  @Override
  public void onMouseDown(MouseDownEvent event) {
    if (isMouseDown) {
      return;
    }
    isMouseDown = true;
    startPos = calculateCellPosition(event);
    clear();
    selectRange(startPos, startPos);
  }

  @Override
  public void onMouseUp(MouseUpEvent event) {
    isMouseDown = false;
    int[] pos = calculateCellPosition(event);
    selectRange(startPos, pos);
  }

  @Override
  public void onMouseMove(MouseMoveEvent event) {
    if (!isMouseDown) {
      return;
    }
    int[] pos = calculateCellPosition(event);
    if (!PointUtils.equals(pos, startPos)) {
      selectRange(startPos, pos);
    }
  }

  /**
   * Calculates the cell position for a given mouse event
   * @param event the mouse event
   * @return the cell position
   */
  private int[] calculateCellPosition(MouseEvent<?> event) {
    int x = event.getRelativeX(getElement());
    int y = event.getRelativeY(getElement());
    // TODO factor row width and height
    int rowPos = (y / (subject.getHeight() / subject.getRowNum()));
    int colPos = (x / (subject.getWidth() / subject.getColNum()));

    return new int[] {rowPos, colPos};
  }

  /**
   * Calculates the top and left coordinates for a given cell position.
   * @param cellPos the cell position
   * @return
   */
  private int[] calculateLeftTop(int[] cellPos) {
    assert cellPos != null : "Cell position cannot be null";
    assert cellPos.length == 2 : "Position length != 2";
    // TODO factor row width and height
    int rowH = (subject.getHeight() / subject.getRowNum());
    int colW = (subject.getWidth() / subject.getColNum());
    return new int[] {cellPos[1] * colW, cellPos[0] * rowH};
  }

  @Override
  public void onResize(WidgetResizeEvent event) {
    //reposition events
  }

}