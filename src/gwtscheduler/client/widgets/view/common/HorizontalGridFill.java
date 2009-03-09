package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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
    private FlowPanel impl;
    private List<Panel> columnWidgets;

    private int columns;
    private int rows;

    /**
     * @param parent
     * @param rows
     * @param cols
     */
    public HorizontalGridFill(Widget parent, int rows, int cols) {
        impl = new FlowPanel();
//        impl.setSize("100%", "100%");
        initWidget(impl);

        this.parent = parent;
        this.columns = cols;
        this.rows = rows;

        this.columnWidgets = new ArrayList<Panel>();

        for (int i = 0; i < cols; i++) {
            FlowPanel flowPanel = new FlowPanel();
            flowPanel.setStyleName("column");// TODO move to css resource
            columnWidgets.add(flowPanel);
        }

    }

    @Override
    protected void onAttach() {
        super.onAttach();

        Element parentEl = parent.getElement();
        int w = parentEl.getOffsetWidth();
        int h = parentEl.getOffsetHeight();
        impl.setPixelSize(w, h);

        int availW = w / columns;
        int[] availableCellSize = getAvailableCellSize();

        // grid construction: first columns, then cell
        for (int c = 0; c < columns; c++) {
            Panel col = columnWidgets.get(c);
            col.setSize(availW + "px", h + "px");
            impl.add(col);

            for (int r = 0; r < rows; r++) {
                int id = (c * r) + r;
                DayWeekCell cell = new DayWeekCell(r, c, "cell: " + id);
                cell.setPixelSize(availableCellSize[0], availableCellSize[1]);
                col.add(cell);
            }
        }
    }

    public void onViewportResize(ViewportResizeEvent event) {
        Element parentEl = parent.getElement();
        int w = parentEl.getOffsetWidth();
        int h = parentEl.getOffsetHeight();
        impl.setPixelSize(w, h);
        
        int[] availableSize = getAvailableCellSize();
        for (Panel column : columnWidgets) {
//            column.setWidth(availableSize[0] + "px");
            column.setSize(availableSize[0] + "px", h + "px");
            for (Iterator<Widget> it = column.iterator(); it.hasNext();) {
                it.next().setPixelSize(getAvailableCellSize()[0], getAvailableCellSize()[1]);
            }
        }
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

        int availW = w / columns;
        int availH = h / rows;
        return new int[] { availW, availH };
    }
}
