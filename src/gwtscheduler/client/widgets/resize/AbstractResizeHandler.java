package gwtscheduler.client.widgets.resize;

import gwtscheduler.client.interfaces.events.IResizeHandler;
import gwtscheduler.client.interfaces.events.ResizeEvent;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractResizeHandler<T extends Widget> implements IResizeHandler {

	/** Wrapper */
	private final T target;

	private ResizeEvent lastEvent;
	private Timer timer;

	/**
	 * Creates a new resize handler for the supplied widget.
	 * 
	 * @param widget the widget thath should handle resizes
	 */
	public AbstractResizeHandler(T widget) {
		target = widget;
		createTimer(333);
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
	 * Creates a timer to check for when to fire the resize.
	 * 
	 * @param timeMillis the repeat time in millis
	 */
	protected void createTimer(int timeMillis) {
		timer = new Timer() {
			@Override
			public void run() {
				if (hasSize()) {
					onDelayedResize(lastEvent);
				}
			}
		};
		timer.scheduleRepeating(timeMillis);
	}

	/**
	 * Indicates if the target widget has any size.
	 * 
	 * @return <code>true</code> if it has size
	 */
	protected boolean hasSize() {
		Element parentEl = getTarget().getElement();
		return parentEl.getOffsetHeight() > 0 && parentEl.getOffsetWidth() > 0;
	}

	/**
	 * Fired when a delayed resize was issued.
	 * 
	 * @param event the resize event
	 */
	protected abstract void onDelayedResize(ResizeEvent event);

	public void onResize(ResizeEvent event) {
		lastEvent = event;
		if (!hasSize()) {
			if (timer == null) {
				createTimer(333);
			}
		} else {
			timer = null;
		}
	}

}
