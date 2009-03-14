package gwtscheduler.client.widgets.view.month.composite;

import gwtscheduler.client.resources.Resources;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;

public class MonthRowTable extends Composite {

    /** number of columns */
    private final int columns;
    /** cached available height */
    private int availableHeight = 0;
    /** table for month cells */
    private FlexTable grid;

    public MonthRowTable(int cols) {
        this.columns = cols;
        grid = new FlexTable();
        grid.setCellPadding(0);
        grid.setCellSpacing(0);

        initWidget(grid);
        setStyleName(Resources.monthCss().monthRowTable());
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        int rowHeight = Resources.monthCss().lineHeight();
        int rows = availableHeight / rowHeight;

        for (int i = 0; i < rows; i++) {
            for (int c = 0; c < columns; c++) {
                // cell (c,i)
                grid.setElement(i, c, createCellElement(i, c));
            }
        }
    }

    /**
     * Redraws the table according to the number of rows.
     * 
     * @param availableHeight
     */
    public void redrawRows(int height) {
        int rowHeight = Resources.monthCss().lineHeight();
        int rows = height / rowHeight;

        if (rows > grid.getRowCount()) {
            ensureRows(rows);
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
        String className = Resources.monthCss().monthCell();
        if (row == 0) {
            className = className + " " + Resources.monthCss().monthCellTitle();
        }
        cell.setClassName(className);
        return cell;
    }

}
