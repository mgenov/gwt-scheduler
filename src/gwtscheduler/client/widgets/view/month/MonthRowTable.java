package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.interfaces.Cell;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.MonthCssResource;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;

//
// /**
// * Defines a month row.
// */
// class MonthRowTable extends Composite {
//
// /** static ref to month css */
// protected static final MonthCssResource CSS = Resources.monthCss();
//
// /** number of columns */
// private final int columns;
// /** cached available height */
// private int availableHeight = 0;
// /** list of cells */
// private List<BaseCell> gridEls;
// /** grid for month cells */
// private FlowPanel grid;
// /** list for title elements */
// private List<Cell<Element>> titleElements;
//
// /**
// * Creates a new month row.
// * @param cols the number of columns in this row
// */
// public MonthRowTable(int cols) {
// this.columns = cols;
//
// grid = new FlowPanel();
// grid.getElement().getStyle().setProperty("display", "inline");
// grid.getElement().getStyle().setProperty("position", "relative");
// grid.getElement().getStyle().setProperty("cssFloat", "left");
// grid.getElement().getStyle().setProperty("float", "left"); //this one's for
// IE
// gridEls = new ArrayList<BaseCell>();
//
// titleElements = new ArrayList<Cell<Element>>();
//
// initWidget(grid);
// setStyleName(CSS.monthRowTable());
// setSize("100%", "100%");
// }
//
// /**
// * Gets the title elements.
// * @return a list of the title elements
// */
// public List<Cell<Element>> getTitleElements() {
// return titleElements;
// }
//
// @Override
// protected void onAttach() {
// super.onAttach();
// DeferredCommand.addCommand(new Command() {
//
// @Override
// public void execute() {
// availableHeight = getElement().getOffsetHeight();
// int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
// int rows = availableHeight / rowHeight;
//
// if (rows + 1 > gridEls.size()) {
// ensureRows(rows + 1);
// }
// }
//
// });
// }
//
// /**
// * Redraws the table according to the number of rows.
// * @param height the available height
// */
// public void redrawRows(int height) {
// int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
// int rows = height / rowHeight;
//
// // +1 is for "rounding", because the rows will never be a perfect fit.
// if (rows + 1 > gridEls.size()) {
// ensureRows(rows + 1);
// }
// }
//
// /**
// * Ensures that this table has the amount of rows necessary.
// * @param rows the number of rows
// */
// private void ensureRows(int rows) {
// // ensure that table has the num of rows specified
// float cellWidth = (float) ((float) (100) / this.columns);
// for (int r = gridEls.size(); r < rows; r++) {
// for (int c = 0; c < columns; c++) {
// for (int i = 0; i < rows; i++) {
// BaseCell cell = createCellElement(r, c);
// cell.getCellElement().getStyle().setProperty("cssFloat", "left");
// cell.getCellElement().getStyle().setProperty("float", "left"); //this one's
// for IE
// cell.getCellElement().getStyle().setProperty("width", cellWidth + "%");
// if (r == 0) {
// titleElements.add(cell);
// }
// grid.add(cell);
// gridEls.add(cell);
// }
// }
// }
// }
//
// /**
// * Creates a cell element.
// * @return the cell element
// */
// private BaseCell createCellElement(int row, int col) {
// BaseCell cell = new BaseCell(row, col);
// cell.getElement().setInnerHTML("xxx");
// if (row == 0) {
// cell.addStyleName(CSS.monthCellTitle());
// } else {
// cell.addStyleName(CSS.monthCell());
// }
// return cell;
// }

class MonthRowTable extends Composite {

  /** static ref to month css */
  protected static final MonthCssResource CSS = Resources.monthCss();

  /** number of columns */
  private final int columns;
  //  /** cached available height */
  //  private int availableHeight = 0;
  /** table for month cells */
  private FlexTable grid;
  /** list for title elements */
  private List<Cell<Element>> titleElements;

  /**
   * Creates a new month row.
   * @param cols the number of columns in this row
   */
  public MonthRowTable(int cols) {
    this.columns = cols;

    grid = new FlexTable();
    grid.setCellPadding(0);
    grid.setCellSpacing(0);

    titleElements = new ArrayList<Cell<Element>>();

    initWidget(grid);
    setStyleName(CSS.monthRowTable());
    getElement().getStyle().setProperty("position", "relative");
  }

  /**
   * Gets the title elements.
   * @return a list of the title elements
   */
  public List<Cell<Element>> getTitleElements() {
    return titleElements;
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    DeferredCommand.addCommand(new Command() {

      @Override
      public void execute() {
        int availableHeight = getElement().getOffsetHeight();
        int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
        int rows = availableHeight / rowHeight;

        if (rows + 1 > grid.getRowCount()) {
          ensureRows(rows + 1);
        }
      }
    });

  }

  /**
   * Redraws the table according to the number of rows.
   * @param height the available height
   */
  void redrawRows(int height) {
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
          BaseCell cell = createCellElement(r, c);
          if (r == 0) {
            titleElements.add(cell);
          }
          grid.setWidget(r, c, cell);
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
    cell.getElement().setInnerHTML("&nbsp;");
    if (row == 0) {
      cell.addStyleName(CSS.monthCellTitle());
    } else {
      cell.addStyleName(CSS.monthCell());
    }
    return cell;
  }

}
