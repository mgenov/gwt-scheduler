package gwtscheduler.client.interfaces.uievents;

/**
 * Interface for resize-aware widgets.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface IHasResizeHandler {

	/**
	 * Gets the resize handler for this instance.
	 * 
	 * @return the resize handler
	 */
	IWidgetResizeHandler getResizeHandler();
}
