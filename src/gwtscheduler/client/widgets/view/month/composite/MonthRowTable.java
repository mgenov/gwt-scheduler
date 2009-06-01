package gwtscheduler.client.widgets.view.month.composite;

import gwtscheduler.client.interfaces.ICell;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.MonthCssResource;
import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

/**
 * Defines a month row.
 */
class MonthRowTable extends Composite {

  /** static ref to month css */
  protected static final MonthCssResource CSS = Resources.monthCss();

  /** number of columns */
  private final int columns;
  /** cached available height */
  private int availableHeight = 0;
  /** table for month cells */
  private FlexTable grid;
  /** list for title elements */
  private List<ICell<Element>> titleElements;

  /**
   * Creates a new month row.
   * @param cols the number of columns in this row
   */
  public MonthRowTable(int cols) {
    this.columns = cols;
    grid = new FlexTable();
    grid.setCellPadding(0);
    grid.setCellSpacing(0);

    titleElements = new ArrayList<ICell<Element>>();

    initWidget(grid);
    setStyleName(CSS.monthRowTable());
  }

  /**
   * Gets the title elements.
   * @return a list of the title elements
   */
  public List<ICell<Element>> getTitleElements() {
    return titleElements;
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
    int rows = availableHeight / rowHeight;

    if (rows + 1 > grid.getRowCount()) {
      ensureRows(rows + 1);
    }
  }

  /**
   * Redraws the table according to the number of rows.
   * @param height the available height
   */
  public void redrawRows(int height) {
    int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
    int rows = height / rowHeight;

    // +1 is for "rounding", because the rows will never be a perfect fit.
    if (rows + 1 > grid.getRowCount()) {
      ensureRows(rows + 1);
    }
  }

  /**
   * Ensures that this table has the amount of rows necessary.
   * @param rows the number of rows
   */
  private void ensureRows(int rows) {
    // ensure that table has the num of rows specified
    for (int r = grid.getRowCount(); r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        for (int i = 0; i < rows; i++) {
          grid.setWidget(r, c, createCellElement(r, c));
        }
      }
    }
  }

  /**
   * Creates a cell element.
   * @return the cell element
   */
  private BaseCell createCellElement(int row, int col) {
    BaseCell cell = new BaseCell(row, col);

    DebugUtils.textRight("cell: " + row + ", " + col, cell.getElement());

    String className = CSS.monthCell();
    cell.addStyleName(className);
    if (row == 0) {
      cell.addStyleName(CSS.monthCellTitle());
      titleElements.add(cell);
    }
    return cell;
  }
}
