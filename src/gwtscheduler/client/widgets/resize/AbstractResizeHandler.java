package gwtscheduler.client.widgets.resize;

import gwtscheduler.client.interfaces.uievents.resize.IWidgetResizeHandler;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResizeHandler<T extends Widget> implements IWidgetResizeHandler {

	/** Wrapper */
	private final T target;

	/**
	 * Creates a new resize handler for the supplied widget.
	 * 
	 * @param widget the widget thath should handle resizes
	 */
	public AbstractResizeHandler(T widget) {
		target = widget;
	}

	/**
	 * Gets the resize handler's target.
	 * 
	 * @return the target
	 */
	protected T getTarget() {
		return target;
	}

	/**
	 * Indicates if the target widget has any size.
	 * 
	 * @return <code>true</code> if it has size
	 */
	protected boolean hasSize() {
		if (!getTarget().isAttached()) {
			return false;
		}

		Element parentEl = getTarget().getElement();
		return parentEl.getOffsetHeight() > 0 && parentEl.getOffsetWidth() > 0;
	}

	/**
	 * Fired when a delayed resize was issued.
	 * 
	 * @param event the resize event
	 */
	protected abstract void onDelayedResize(WidgetResizeEvent event);

	public void onResize(final WidgetResizeEvent event) {
	}

}
