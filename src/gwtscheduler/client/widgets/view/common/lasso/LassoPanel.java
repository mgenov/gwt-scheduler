package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

/**
 * Lasso panel. Responsible for displaying user lasso selections.
 * @author malp
 */
class LassoPanel extends AbsolutePanel implements MouseDownHandler, MouseMoveHandler, MouseUpHandler {

  /** the lasso subject grid */
  private LassoSubject subject;
  /** indicates if a lasso is being selected or not */
  private boolean isMouseDown = false;
  /** the lasso starting position */
  private int[] startPos;

  /**
   * Default constructor.
   */
  LassoPanel() {
    // style
    addStyleName(Resources.dayWeekCss().lasso());

    addDomHandler(this, MouseDownEvent.getType());
    addDomHandler(this, MouseUpEvent.getType());
    addDomHandler(this, MouseMoveEvent.getType());
    
    DOM.setStyleAttribute(getElement(), "opacity", "0.5");
    DOM.setStyleAttribute(getElement(), "filter", "alpha(opacity=50)");
  }

  /**
   * Selects a range of lasso cells.
   * @param p1 the first position
   * @param p2 the second position
   */
  private void selectRange(int[] p1, int[] p2) {
    Label label = new Label("x");
    label.getElement().getStyle().setProperty("border", "1px solid red");
    label.getElement().getStyle().setProperty("opacity", "1.0");
    label.getElement().getStyle().setProperty("filter", "alpha(opacity=100)");
    int[] coords = calculateLeftTop(p1);
    
    add(label, coords[0], coords[1]);
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
    if(isMouseDown) {
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
    if (!equals(pos, startPos)) {
      selectRange(startPos, pos);
    }
  }

  @Override
  public void onMouseMove(MouseMoveEvent event) {
    if (!isMouseDown) {
      return;
    }
    int[] pos = calculateCellPosition(event);
    if (!equals(pos, startPos)) {
      selectRange(startPos, pos);
    }
  }

  /**
   * Compares two positions.
   * @param p1 the first position
   * @param p2 the second position
   * @return <code>true</code> if the two positions are the same
   */
  private boolean equals(int[] p1, int[] p2) {
    assert p1 != null : "Cannot compare a null position";
    assert p2 != null : "Cannot compare a null position";

    assert p1.length == 2 : "Position length != 2";
    assert p2.length == 2 : "Position length != 2";

    return (p1[0] == p2[0] && p1[1] == p2[1]);
  }

  /**
   * Calculates the cell position for a given mouse event
   * @param event the mouse event
   * @return the cell position
   */
  private int[] calculateCellPosition(MouseEvent<?> event) {
    assert subject != null : "Lasso subject cannot be null.";

    int x = event.getRelativeX(getElement());
    int y = event.getRelativeY(getElement());
    //TODO factor row width and height
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
    //TODO factor row width and height
    int rowH = (subject.getHeight() / subject.getRowNum());
    int colW = (subject.getWidth() / subject.getColNum());
    return new int[] {cellPos[1] * colW, cellPos[0] * rowH};
  }

}