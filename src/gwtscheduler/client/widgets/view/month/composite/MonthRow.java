package gwtscheduler.client.widgets.view.month.composite;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Represents a month row.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthRow extends Composite implements IViewportResizeHandler {

    /** impl */
    private FlowPanel impl;
    /** will hold grid data */
    private MonthRowTable grid;
    /** number of cols */
    private final int columns;

    /**
     * Default constructor.
     * 
     * @param cols the number of columns
     */
    public MonthRow(int cols) {
        columns = cols;
        impl = new FlowPanel();

        grid = new MonthRowTable(columns);
        impl.add(grid);
        initWidget(impl);
        
        setStyleName(Resources.monthCss().monthRow());
    }

    public void onViewportResize(ViewportResizeEvent event) {
        if (!isVisible()) {
            return;
        }
        int availableHeight = getElement().getOffsetHeight();
        grid.redrawRows(availableHeight);
    }

}
