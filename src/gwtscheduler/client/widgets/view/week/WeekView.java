package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrappedWidget;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Utility class that fills the whole viewport
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WeekView extends WrappedWidget implements IViewportResizeHandler {

    /** main container */
    private VerticalPanel container;
    /** Grid for days/hours */
    private HorizontalGridFill grid;

    /**
     * Default constructor.
     */
    public WeekView() {
        container = new VerticalPanel();
        wrapWidget(container);

        grid = new HorizontalGridFill(48, 7);
        container.add(grid);
    }

    public void onViewportResize(ViewportResizeEvent event) {
        container.setSize("100%", "96em");// 24h*4 lines
        grid.onViewportResize(event);
    }

}
