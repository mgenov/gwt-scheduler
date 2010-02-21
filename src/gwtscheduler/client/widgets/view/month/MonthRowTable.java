package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.MonthCssResource;
import gwtscheduler.client.widgets.common.Cell;
import gwtscheduler.client.widgets.view.common.cell.BaseCell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author malp
 *
 */
class MonthRowTable extends Composite {

  /** static ref to month css */
  protected static final MonthCssResource CSS = Resources.monthCss();

  /** table for month cells */
  @UiField
  FlexTable grid;
  /** number of columns */
  private final int columns;
  /** list for title elements */
  private List<Cell<Element>> titleElements;

  /** ui binder instance */
  private static MonthRowTableUiBinder uiBinder = GWT.create(MonthRowTableUiBinder.class);

  /** ui binder interface */
  interface MonthRowTableUiBinder extends UiBinder<Widget, MonthRowTable> {
  }

  /**
   * Creates a new month row.
   * @param cols the number of columns in this row
   */
  public MonthRowTable(int cols) {
    this.columns = cols;
    initWidget(uiBinder.createAndBindUi(this));

    grid.setCellPadding(0);
    grid.setCellSpacing(0);

    titleElements = new ArrayList<Cell<Element>>();
    setStyleName(CSS.monthRowTable());
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
