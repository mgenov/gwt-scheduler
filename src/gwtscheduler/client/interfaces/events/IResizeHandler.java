package gwtscheduler.client.interfaces.events;

/**
 * Interface for resize-aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IResizeHandler {

	/**
	 * Fired when the parent widget was resized.
	 * 
	 * @param event the resize event
	 */
	void onResize(ResizeEvent event);
}
