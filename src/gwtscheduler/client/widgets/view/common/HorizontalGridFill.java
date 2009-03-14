package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Enter class description here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class HorizontalGridFill extends Composite implements IViewportResizeHandler {

    private Widget parent;
    private HTMLTable impl;
    private List<Panel> columnWidgets;

    private int columns;
    private int rows;

    /**
     * @param parent
     * @param rows
     * @param cols
     */
    public HorizontalGridFill(Widget parent, int rows, int cols) {
        impl = new Grid(1, cols + 1);
        impl.setBorderWidth(0);
        impl.setStyleName(Resources.dayWeekCss().horizontalFillGrid());

        initWidget(impl);

        this.parent = parent;
        this.columns = cols;
        this.rows = rows;

        this.columnWidgets = new ArrayList<Panel>();

        // here we add one column for each day
        // one more col for cell labels
        for (int i = 0; i < cols + 1; i++) {
            FlowPanel flowPanel = new FlowPanel();
            if (i == 0) {
                flowPanel.setStyleName(Resources.dayWeekCss().titleColumn());
            } else {
                flowPanel.setStyleName(Resources.dayWeekCss().column());
            }
            columnWidgets.add(flowPanel);
            impl.setWidget(0, i, flowPanel);
        }

    }

    @Override
    protected void onAttach() {
        super.onAttach();

        Element parentEl = parent.getElement();
        int width = parentEl.getOffsetWidth();
        int height = parentEl.getOffsetHeight();
        impl.setPixelSize(width, height);

        int remainingColWidth = (width - getTitleColumnWidth()) / columns;
        // int remainingColWidth = width / columns;
        int[] availableCellSize = getAvailableCellSize(width, height);

        // grid construction: first columns, then cell
        // we skip title column

        Panel titlePanel = columnWidgets.get(0); // first col is title
        for (int r = 0; r < rows; r++) {
            FlowPanel title = new FlowPanel();
            title.setStyleName(Resources.dayWeekCss().titleCell());
            titlePanel.add(title);
            // TODO set height
        }

        for (int c = 1; c < columns; c++) {
            Panel col = columnWidgets.get(c); // first col is title
            col.setSize(remainingColWidth + "px", height + "px");

            for (int r = 0; r < rows; r++) {
                int id = ((c - 1) * r) + r;
                DayWeekCell cell = new DayWeekCell(r, (c - 1), "cell: " + id);
                cell.setPixelSize(availableCellSize[0], availableCellSize[1]);
                col.add(cell);
            }
        }
    }

    /**
     * @return
     */
    private final int getTitleColumnWidth() {
        return Resources.dayWeekCss().titleColumnWidthPx();
    }

    public void onViewportResize(ViewportResizeEvent event) {
        Element parentEl = parent.getElement();
        int w = parentEl.getOffsetWidth();
        int h = parentEl.getOffsetHeight();
        impl.setPixelSize(w, h);

        int[] availableSize = getAvailableCellSize();
        // for (Panel column : columnWidgets) {
        for (int i = 1; i < columnWidgets.size(); i++) {
            Panel column = columnWidgets.get(i);
            column.setSize(availableSize[0] + "px", h + "px");
            for (Iterator<Widget> it = column.iterator(); it.hasNext();) {
                it.next().setPixelSize(availableSize[0], availableSize[1]);
            }
        }
    }

    private int[] getAvailableCellSize(int w, int h) {
        int availW = (w - getTitleColumnWidth()) / columns;
        int availH = h / rows;
        return new int[] { availW, availH };
    }

    /**
     * Gets the available cell size.
     * 
     * @return the available width and height
     */
    private int[] getAvailableCellSize() {
        Element parentEl = parent.getElement();
        int w = parentEl.getOffsetWidth();
        int h = parentEl.getOffsetHeight();

        int availW = (w - getTitleColumnWidth()) / columns;
        int availH = h / rows;
        return new int[] { availW, availH };
    }
}
