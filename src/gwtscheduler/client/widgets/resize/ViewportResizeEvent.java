package gwtscheduler.client.widgets.resize;

/**
 * Resize event for resize aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class ViewportResizeEvent {

    public final int width;
    public final int height;

    /**
     * Main constructor.
     * 
     * @param availableWidth the available width
     * @param availableHeight available height
     */
    public ViewportResizeEvent(int availableWidth, int availableHeight) {
        this.width = availableWidth;
        this.height = availableHeight;
    }
}
