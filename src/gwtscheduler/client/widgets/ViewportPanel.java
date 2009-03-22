package gwtscheduler.client.widgets;

import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a viewport to calendar events.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class ViewportPanel extends Composite implements ResizeHandler {

	/** main container */
	private ScrollPanel container;
	/** the minimum size for the target */
	private final int minWidth, minHeight;
	/** cached vals to prevent firing multiple resizes for same window size */
	private int lastWindowWidth, lastWindowHeight;
	/** widget collection for resize events */
	private List<IViewportResizeHandler> resizeHandlers;

	/**
	 * Default constructor.
	 */
	public ViewportPanel() {
		this(-1, -1);
	}

	/**
	 * Another constructor.
	 * 
	 * @param parent
	 * @param minWidth the minimum width
	 * @param minHeight the minimum height
	 */
	public ViewportPanel(int minWidth, int minHeight) {
		this.minWidth = minWidth;
		this.minHeight = minHeight;

		// we never show hscroll
		container = new ScrollPanel();
		container.getElement().getStyle().setProperty("overflowX", "hidden");
		initWidget(container);

		Window.addResizeHandler(this);
	}

	/**
	 * Resizes the main target in order to occupy all available space.
	 * 
	 * @param viewporWidth the available viewport width
	 * @param viewportHeight the available viewport height
	 */
	void doResize(int viewporWidth, int viewportHeight) {
		// if (!isAttached()) {
		// resizeQueued = true;
		// return;
		// }
		if (lastWindowWidth == viewporWidth && lastWindowHeight == viewportHeight) {
			return;
		}
		lastWindowWidth = viewporWidth;
		lastWindowHeight = viewportHeight;

		// TODO this code assumes the parent goes to the right of the screen
		int maxWidth = viewporWidth - getWidget().getAbsoluteLeft();
		int maxHeight = viewportHeight - getWidget().getAbsoluteTop();

		maxWidth = Math.max(maxWidth, minWidth);// - Constants.SCROLLBAR_WIDTH;
		maxHeight = Math.max(maxHeight, minHeight);

		// // check if we are not resizing for the same dimensions
		// if (maxWidth == lastWidth && maxHeight == lastHeight) {
		// return;
		// }
		if (maxWidth > 0) {
			setWidth(maxWidth + "px");
		}
		if (maxHeight > 0) {
			setHeight(maxHeight + "px");
		}
		if (maxWidth > 0 || maxHeight > 0) {
			fireResizeEvent(maxWidth, maxHeight);
		}

		// lastWidth = maxWidth;
		// lastHeight = maxHeight;
		// resizeQueued = false;
	}

	/**
	 * Fires the resize events.
	 * 
	 * @param w the width
	 * @param h the height
	 */
	private void fireResizeEvent(int w, int h) {
		// don't work with negative values
		if (w <= 0 || h <= 0) {
			return;
		}

		if (resizeHandlers != null) {
			ViewportResizeEvent event = new ViewportResizeEvent(w, h);
			for (IViewportResizeHandler ira : resizeHandlers) {
				ira.onViewportResize(event);
			}
		}
	}

	/**
	 * Adds a widget to this viewport.
	 * 
	 * @param w the widget
	 */
	public void add(Widget w) {
		container.add(w);
	}

	/**
	 * Adds a widget to this viewport.
	 * 
	 * @param w the widget
	 * @param handler the resize handler
	 */
	public void add(Widget w, IViewportResizeHandler handler) {
		container.add(w);
		if (resizeHandlers == null) {
			resizeHandlers = new ArrayList<IViewportResizeHandler>();
		}
		resizeHandlers.add(handler);
	}

	public void onResize(final ResizeEvent event) {
		doDeferredResize();
	}

	/**
	 * Handles resize through a deferred command.
	 */
	protected void doDeferredResize() {
		final int[] availableSize = DOMUtils.getViewportDimensions();
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				doResize(availableSize[0], availableSize[1]);
			}
		});
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		doDeferredResize();
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		doDeferredResize();
	}

}
