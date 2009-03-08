package gwtscheduler.client.widgets.resize;

import com.google.gwt.user.client.ui.Widget;

/**
 * Handlers resize events for the wrapper widget.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DefaultViewportResizeHandler implements IViewportResizeHandler {

    /** Wrapper */
    private final Widget target;

    /**
     * Creates a new resize handler for the supplied widget.
     * 
     * @param widget the widget thath should handle resizes
     */
    public DefaultViewportResizeHandler(Widget widget) {
        target = widget;
    }

    /**
     * Called when a viewport resize event ocurred.
     * 
     * @param event the resize event
     */
    public void onViewportResize(ViewportResizeEvent event) {
        target.setSize(event.width + "px", event.height + "px");
    }
}
