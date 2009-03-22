package gwtscheduler.client.widgets.view.month.composite;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.MonthCssResource;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;

/**
 * Defines a month row.
 */
public class MonthRowTable extends Composite {

    /** static ref to month css */
    protected static final MonthCssResource CSS = Resources.monthCss();

    /** number of columns */
    private final int columns;
    /** cached available height */
    private int availableHeight = 0;
    /** table for month cells */
    private FlexTable grid;

    /**
     * Creates a new month row.
     * 
     * @param cols the number of columns in this row
     */
    public MonthRowTable(int cols) {
        this.columns = cols;
        grid = new FlexTable();
        grid.setCellPadding(0);
        grid.setCellSpacing(0);

        initWidget(grid);
        setStyleName(CSS.monthRowTable());
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
     * 
     * @param height the available height
     */
    public void redrawRows(int height) {
        int rowHeight = CSS.lineHeight() + CSS.monthCellPadTopPx();
        int rows = height / rowHeight;

        // +1 is for rounding,because the rows will never be a perfect fit...
        if (rows + 1 > grid.getRowCount()) {
            ensureRows(rows + 1);
        }
    }

    /**
     * Ensures that this table has the amount of rows necessary.
     * 
     * @param rows the number of rows
     */
    private void ensureRows(int rows) {
        // ensure that table has the num of rows specified
        for (int r = grid.getRowCount(); r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                for (int i = 0; i < rows; i++) {
                    grid.setElement(r, c, createCellElement(r, c));
                }
            }
        }
    }

    /**
     * Creates a cell element.
     * 
     * @return the cell element
     */
    private Element createCellElement(int row, int col) {
        Element cell = DOM.createDiv();
        cell.setInnerText("cell: " + row + ", " + col);

        // TODO add a way to decorate cells
        String className = CSS.monthCell();
        if (row == 0) {
            className = className + " " + CSS.monthCellTitle();
        }
        cell.setClassName(className);
        return cell;
    }

}
