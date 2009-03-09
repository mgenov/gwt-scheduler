package gwtscheduler.client.widgets.view.month;

import gwtscheduler.client.widgets.resize.DefaultViewportResizeHandler;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;
import gwtscheduler.client.widgets.view.WrappedWidget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * View class for months.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class MonthView extends WrappedWidget implements IViewportResizeHandler {

    /** Main container */
    private FlowPanel container;

    /** resize handler */
    private DefaultViewportResizeHandler handler;

    public MonthView() {
        container = new FlowPanel();
        handler = new DefaultViewportResizeHandler(this);
        wrapWidget(container);

        container.add(new Label("month"));
    }

    public void onViewportResize(ViewportResizeEvent event) {
        // we delegate to default handler
        handler.onViewportResize(event);
    }
}
