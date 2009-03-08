package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.utils.DebugUtils;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrapperWidget;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Utility class that fills the whole viewport
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WeekView extends WrapperWidget implements IViewportResizeHandler {

    /** main container */
    private FlowPanel container;

    /**
     * Default constructor.
     */
    public WeekView() {
        container = new FlowPanel();
        wrapWidget(container);
        DebugUtils.addBgColor(container.getElement());
    }

    public void onViewportResize(ViewportResizeEvent event) {
        container.setSize(event.width + "px", "96em");// 24h*4 lines
    }

}
