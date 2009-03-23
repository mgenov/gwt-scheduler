package gwtscheduler.client.interfaces.events;


/**
 * Interface for resize-aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IViewportResizeHandler {

    
    /**
     * Fired when the viewport was resized.
     * 
     * @param event the resize event
     */
    void onViewportResize(ViewportResizeEvent event);
}
