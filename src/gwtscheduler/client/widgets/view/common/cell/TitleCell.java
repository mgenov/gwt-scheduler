package gwtscheduler.client.widgets.view.common.cell;

/**
 * Used as a row title cell. Typically will display hours.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class TitleCell extends DayCell {

  /**
   * Creates a new label.
   * @param id the cell id
   * @param label the label
   */
  public TitleCell(int row, int col, String label) {
    super(row, col, label);
    setStyleName(row % 2 == 0 ? CSS.evenTitleCell() : CSS.oddTitleCell());
  }

  @Override
  public void setCompensatedPixelSize(int w, int h) {
    if (w < 0) {
      w = 0;
    }
    if (h < 0) {
      h = 0;
    }
    // width - padding; height - border
    setPixelSize(w /*- CSS.smallPaddingPx()*/, h - CSS.smallBorderPx());
  }

}
