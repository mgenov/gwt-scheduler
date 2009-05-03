package gwtscheduler.client.widgets.view.common.cell;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

/**
 * Serves as a row label.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DayWeekCell extends Widget {
  /** static ref to css */
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /** Cell identifiers */
  public final int row, col;

  /**
   * Creates a new label.
   * 
   * @param row the row index
   * @param col the column index
   * @param label the label
   */
  public DayWeekCell(int row, int col, String label) {
    this.row = row;
    this.col = col;
    setElement(DOM.createDiv());

    // getElement().setPropertyInt("row", row);
    // getElement().setPropertyInt("col", col);

    setStyleName(row % 2 == 0 ? CSS.evenCell() : CSS.oddCell());
    getElement().setInnerText(label);
  }

  /**
   * Sets the pixel size, but takes in account borders and padding.
   * 
   * @param w the width
   * @param h the height
   */
  public void setCompensatedPixelSize(int w, int h) {
    if (w < 0) {
      w = 0;
    }
    if (h < 0) {
      h = 0;
    }
    // width - border - padding; height - border
    final int width = w - CSS.mediumBorderPx() - CSS.smallPaddingPx();
    final int height = h - CSS.smallBorderPx();
    setPixelSize(width, height);
  }
}
