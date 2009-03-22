package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.common.cell.DayWeekCell;
import gwtscheduler.client.widgets.view.common.cell.TitleCell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.LazyPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Horizontal Grid class.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFill extends LazyPanel implements IViewportResizeHandler {
    /** static ref to css */
    private static final DayWeekCssResource CSS = Resources.dayWeekCss();

    /** class impl */
    private HTMLTable impl;
    /** columns */
    private List<Panel> columnWidgets;

    /** grid col count, excluding title column */
    private int columns;
    /** grid row count */
    private int rows;

    /**
     * Creates a new grid with the supplied dimensions. An additional column will be added to supply a title.
     * 
     * @param rows the number of rows
     * @param cols the number of columns
     */
    public HorizontalGridFill(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
    }

    @Override
    protected Widget createWidget() {
        impl = new Grid(1, this.columns + 1);
        impl.setBorderWidth(0);
        impl.setStyleName(CSS.horizontalFillGrid());

        columnWidgets = new ArrayList<Panel>();

        // here we add one column for each day
        // one more col for cell labels
        for (int i = 0; i < this.columns + 1; i++) {
            FlowPanel flowPanel = new FlowPanel();
            String className = null;
            className = (i == 0) ? CSS.titleColumn() : CSS.column();
            flowPanel.setStyleName(className);
            columnWidgets.add(flowPanel);
            impl.setWidget(0, i, flowPanel);
        }

        // create titel cells
        Panel titlePanel = columnWidgets.get(0); // first col is title
        for (int r = 0; r < rows; r++) {
            TitleCell title = new TitleCell(r, 0, r + "");
            titlePanel.add(title);
        }

        // regular cells are different from title cells
        for (int c = 1; c < columns + 1; c++) {
            Panel col = columnWidgets.get(c);

            for (int r = 0; r < rows; r++) {
                int id = ((c - 1) * r) + c;
                DayWeekCell cell = new DayWeekCell(r, (c - 1), "cell: " + id);
                col.add(cell);
            }
        }
        return impl;

    }

    @Override
    protected void onAttach() {
        ensureWidget();
        super.onAttach();
    }

    /**
     * Gets the title column width for the title column.
     * 
     * @return the title column width
     */
    private final int getTitleColumnWidth() {
        return CSS.titleColumnWidthPx();
    }

    /**
     * Gets the title column width for the title column, including borders and padding.
     * 
     * @return the title column offset width
     */
    private int getTitleColumnOffsetWidth() {
        return getTitleColumnWidth() + CSS.smallPaddingPx();
    }

    public void onViewportResize(ViewportResizeEvent event) {
        if (isAttached()) {
            doResize(event);
        }
    }

    /**
     * Handles resize.
     * 
     * @param event the resize event
     */
    protected void doResize(ViewportResizeEvent event) {
        Element parentEl = getParent().getElement();
        int height = parentEl.getOffsetHeight();
        //we can't use the offset with
        // int width = parentEl.getOffsetWidth();
        int width = event.width;

        if (width <= 0 || height <= 0) {
            return;
        }

        impl.setPixelSize(width - Constants.SCROLLBAR_WIDTH, height);
        int[] availableSize = getCellSize(width - Constants.SCROLLBAR_WIDTH, height);
        int remainingColWidth = ((width - getTitleColumnOffsetWidth()) / columns) - Constants.SCROLLBAR_WIDTH;
//        remainingColWidth = remainingColWidth - CSS.smallBorderPx();
        for (int i = 0; i < columnWidgets.size(); i++) {
            Panel column = columnWidgets.get(i);
            if (i == 0) {
                column.setPixelSize(getTitleColumnOffsetWidth(), height);
            } else {
                column.setPixelSize(remainingColWidth, height);
            }

            // resize cells
            for (Iterator<Widget> it = column.iterator(); it.hasNext();) {
                DayWeekCell cell = (DayWeekCell) it.next();
                if (i == 0) {
                    cell.setCompensatedPixelSize(getTitleColumnWidth(), availableSize[1]);
                } else {
                    cell.setCompensatedPixelSize(availableSize[0], availableSize[1]);
                }
            }
        }
    }

    /**
     * Gets the default cell size for a non-title cell.
     * 
     * @param parentWidth the parent width
     * @param parentHeight the parent height
     * @return the cell size, without counting with borders or padding
     */
    private int[] getCellSize(int parentWidth, int parentHeight) {
        // TODO fix the scrollbar width detection
        int availW = ((parentWidth - getTitleColumnOffsetWidth()) / columns);
        int availH = parentHeight / rows;
        return new int[] { availW, availH };
    }
}
