package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrappedWidget;
import gwtscheduler.client.widgets.view.common.HorizontalGridFill;

import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Enter class description here.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DayView extends WrappedWidget implements IViewportResizeHandler {

    /** Main container */
    private VerticalPanel container;
    /** Hours grid */
    private HorizontalGridFill grid;

    /**
     * Default constructor.
     */
    public DayView() {
        container = new VerticalPanel();
        wrapWidget(container);

        grid = new HorizontalGridFill(this, 48, 1);
        container.add(grid);
    }

    public void onViewportResize(ViewportResizeEvent event) {
        container.setSize("100%", "96em");// 24h*4 lines
        grid.onViewportResize(event);
    }
}
