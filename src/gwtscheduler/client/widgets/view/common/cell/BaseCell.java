package gwtscheduler.client.widgets.view.common.cell;

import gwtscheduler.client.dragndrop.DropEvent;
import gwtscheduler.client.dragndrop.DropHandler;
import gwtscheduler.client.widgets.common.Cell;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base class for grid cells.
 * @author malp
 */
public class BaseCell extends Widget implements Cell<Element> {

  /** Cell identifiers */
  public int row, col;
  @UiField
  DivElement rootElement;

  private static BaseCellUiBinder uiBinder = GWT.create(BaseCellUiBinder.class);

  interface BaseCellUiBinder extends UiBinder<DivElement, BaseCell> {
  }

  /**
   * Default constructor.
   * @param row the row index
   * @param col the column index
   */
  public BaseCell(int row, int col) {
    setElement(uiBinder.createAndBindUi(this));
    this.row = row;
    this.col = col;
  }

  public int column() {
    return col;
  }

  public int row() {
    return row;
  }

  public final Element getCellElement() {
    return getElement();
  }

  public int index(int rowsize) {
    return row * rowsize + col;
  }

}
