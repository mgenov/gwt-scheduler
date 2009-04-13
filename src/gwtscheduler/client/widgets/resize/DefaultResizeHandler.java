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
public class DefaultResizeHandler extends AbstractResizeHandler<Widget> implements IResizeHandler {

	/**
	 * Creates a new resize handler for the supplied widget.
	 * 
	 * @param widget the widget thath should handle resizes
	 */
	public DefaultResizeHandler(Widget widget) {
		super(widget);
	}

	/**
	 * Called when a viewport resize event ocurred.
	 * 
	 * @param event the resize event
	 */
	public void onResize(ResizeEvent event) {
		getTarget().setSize(event.width + "px", event.height + "px");
	}

	@Override
	protected void onDelayedResize(ResizeEvent event) {
		getTarget().setSize(event.width + "px", event.height + "px");
	}

}
