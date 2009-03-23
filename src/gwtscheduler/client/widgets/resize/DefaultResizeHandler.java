package gwtscheduler.client.widgets.resize;

import gwtscheduler.client.interfaces.events.IResizeHandler;
import gwtscheduler.client.interfaces.events.ResizeEvent;

import com.google.gwt.user.client.ui.Widget;

/**
 * Handlers resize events for the wrapper widget.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DefaultResizeHandler implements IResizeHandler {

    /** Wrapper */
    private final Widget target;

    /**
     * Creates a new resize handler for the supplied widget.
     * 
     * @param widget the widget thath should handle resizes
     */
    public DefaultResizeHandler(Widget widget) {
        target = widget;
    }

    /**
     * Called when a viewport resize event ocurred.
     * 
     * @param event the resize event
     */
    public void onResize(ResizeEvent event) {
        target.setSize(event.width + "px", event.height + "px");
    }
}
