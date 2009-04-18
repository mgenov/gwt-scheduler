package gwtscheduler.client.interfaces.events;

/**
 * Resize event for resize aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class ViewportResizeEvent extends WidgetResizeEvent {

	/**
	 * Main constructor.
	 * 
	 * @param availableWidth the available width
	 * @param availableHeight available height
	 */
	public ViewportResizeEvent(int availableWidth, int availableHeight) {
		super(availableWidth, availableHeight);
	}
}
